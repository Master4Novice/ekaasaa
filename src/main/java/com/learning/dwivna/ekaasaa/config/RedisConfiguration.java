package com.learning.dwivna.ekaasaa.config;

import com.learning.dwivna.ekaasaa.data.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfiguration {

//    @Bean
//    public JedisConnectionFactory connectionFactory() {
//        return new JedisConnectionFactory();
//    }

    @Bean
    public ReactiveRedisTemplate<String, User> reactiveUserRedisTemplate(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<User> serializer = new Jackson2JsonRedisSerializer<>(User.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, User> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, User> context = builder.value(serializer).build();

        return new ReactiveRedisTemplate<>(factory, context);
    }

    @Bean
    public ReactiveValueOperations<String, User> redisValueUserOperations(ReactiveRedisTemplate<String, User> reactiveUserRedisTemplate) {
        return reactiveUserRedisTemplate.opsForValue();
    }
}
