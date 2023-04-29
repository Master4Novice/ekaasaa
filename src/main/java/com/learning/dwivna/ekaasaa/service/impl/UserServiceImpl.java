package com.learning.dwivna.ekaasaa.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.learning.dwivna.ekaasaa.data.User;
import com.learning.dwivna.ekaasaa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private ReactiveRedisTemplate<String, User> reactiveUserRedisTemplate;

    @Autowired
    private ReactiveValueOperations<String, User> redisValueUserOperations;

    @Autowired
    private ReactiveRedisMessageListenerContainer reactiveMsgListenerContainer;

    @Autowired
    private ChannelTopic channelTopic;

    @Override
    public Mono<User> getUser(String id) {
        return redisValueUserOperations.get(id);
    }

    @Override
    public Mono<String> putUser(User user) {
        user.setId(UUID.randomUUID().toString());
        Mono<Boolean> result = redisValueUserOperations.set(user.getId(), user);
        return Boolean.TRUE.equals(result.block()) ? Mono.just(String.format("User added with ID:{%s}", user.getId())) : Mono.just("User addition failed");
    }

    @Override
    public Mono<User> updateUser(String id, User user) {
        user.setId(id);
        redisValueUserOperations.getAndSet(id, user).block();
        return Mono.just(user);
    }

    @Override
    public Mono<String> deleteUser(String id) {
        Mono<Boolean> result = redisValueUserOperations.delete(id);
        return Boolean.TRUE.equals(result.block()) ? Mono.just(String.format("User deleted having ID:{%s}", id)) : Mono.just("User deletion failed");
    }

    @Override
    public Publisher<List<User>> userSub() {
        Flux<String> flux = reactiveMsgListenerContainer.receive(this.channelTopic)
                .map(ReactiveSubscription.Message::getMessage)
                .map(msg -> {
                    System.out.println(msg);
                    return msg;
                });
        log.info("userSub Message:{}", flux.blockFirst());
        Gson gson = new Gson();
        Type typeOfObjectsList = new TypeToken<List<User>>() {
        }.getType();
        List<User> users = gson.fromJson(flux.blockFirst(), typeOfObjectsList);
        log.info("Message List:{}", users);
        return Flux.just(users);
    }

    @Override
    public Mono<List<User>> getUsers() {
        List<String> keys = reactiveUserRedisTemplate.keys("*").collectList().block();
        List<User> userList = new ArrayList<>();
        if (keys != null && !keys.isEmpty()) {
            keys.forEach(key -> {
                userList.add(this.getUser(key).block());
            });
        }
        return Mono.just(userList);
    }
}
