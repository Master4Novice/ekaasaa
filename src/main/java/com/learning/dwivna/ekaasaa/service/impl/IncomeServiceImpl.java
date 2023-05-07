package com.learning.dwivna.ekaasaa.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.learning.dwivna.ekaasaa.data.Income;
import com.learning.dwivna.ekaasaa.data.User;
import com.learning.dwivna.ekaasaa.service.IncomeService;
import com.learning.dwivna.ekaasaa.service.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private ReactiveRedisTemplate<String, User> reactiveUserRedisTemplate;

    @Autowired
    private ReactiveValueOperations<String, User> redisValueUserOperations;

    @Autowired
    private ReactiveRedisMessageListenerContainer reactiveMsgListenerContainer;

    @Autowired
    private ChannelTopic channelTopic;

    @Autowired
    private PublisherService publisherService;

    private final Gson gson = new GsonBuilder().create();


    @Override
    public Mono<String> putUserIncomes(String userId, List<Income> incomes) {
        return null;
    }

    @Override
    public Mono<User> updateUserIncomes(String userId, List<Income> incomes) {
        return null;
    }

    @Override
    public Mono<String> deleteUserIncome(String id) {
        return null;
    }
}
