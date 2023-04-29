package com.learning.dwivna.ekaasaa.publish;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.learning.dwivna.ekaasaa.data.User;
import com.learning.dwivna.ekaasaa.service.PublisherService;
import com.learning.dwivna.ekaasaa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ApplicationPublisher {

    @Autowired
    private ReactiveRedisTemplate<String, User> reactiveUserRedisTemplate;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private UserService userService;

    @Scheduled(fixedDelay = 10000)
    public void subscribe() {
        List<String> keys = reactiveUserRedisTemplate.keys("*").collectList().block();
        List<User> userList = new ArrayList<>();
        if (keys != null && !keys.isEmpty()) {
            keys.forEach(key -> {
                userList.add(userService.getUser(key).block());
            });
        }
        Gson gson = new GsonBuilder().create();
        log.info("Size of fetched keys is {}", userList.size());
        publisherService.publish(gson.toJson(userList));
    }
}
