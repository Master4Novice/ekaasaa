package com.learning.dwivna.ekaasaa.config;

import com.learning.dwivna.ekaasaa.data.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class UserRedisConfiguration implements MasterRedis<String, User>{

    @Override
    @Bean("reactiveUserRedisTemplate")
    public ReactiveRedisTemplate<String, User> reactiveMasterRedisTemplate(@Qualifier("reactiveRedisConnectionFactory") ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<User> serializer = new Jackson2JsonRedisSerializer<>(User.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, User> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
        RedisSerializationContext<String, User> context = builder.value(serializer).build();
        log.info("Creating Bean {}...", "reactiveUserRedisTemplate");
        return new ReactiveRedisTemplate<>(factory, context);
    }

    @Bean("reactiveUserValueOperations")
    public ReactiveValueOperations<String, User> reactiveValueOperations(@Qualifier("reactiveUserRedisTemplate") ReactiveRedisTemplate<String, User> reactiveMasterRedisTemplate){
       return reactiveMasterRedisTemplate.opsForValue();
    }
}
