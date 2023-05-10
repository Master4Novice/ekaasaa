package com.learning.dwivna.ekaasaa.config;

import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Indexed;

@Indexed
public interface MasterRedis<Key, Entity> {
    ReactiveRedisTemplate<Key, Entity> reactiveMasterRedisTemplate(ReactiveRedisConnectionFactory factory);
}