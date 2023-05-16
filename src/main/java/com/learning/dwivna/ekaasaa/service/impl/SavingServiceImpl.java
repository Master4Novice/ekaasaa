package com.learning.dwivna.ekaasaa.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.learning.dwivna.ekaasaa.data.Saving;
import com.learning.dwivna.ekaasaa.data.User;
import com.learning.dwivna.ekaasaa.service.PublisherService;
import com.learning.dwivna.ekaasaa.service.SavingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class SavingServiceImpl implements SavingService {

    @Autowired
    private ReactiveRedisTemplate<String, User> reactiveUserRedisTemplate;

    @Autowired
    private ReactiveRedisMessageListenerContainer reactiveMsgListenerContainer;

    @Autowired
    private ChannelTopic channelTopic;

    @Autowired
    private PublisherService publisherService;

    private final Gson gson = new GsonBuilder().create();


    @Override
    public Mono<String> putUserSavings(String userId, Saving savings) {
        return null;
    }

    @Override
    public Mono<User> updateUserSavings(String userId, Saving savings) {
        return null;
    }

    @Override
    public Mono<String> deleteUserSaving(String id) {
        return null;
    }
}
