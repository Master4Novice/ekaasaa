package com.learning.dwivna.ekaasaa.config;

import com.learning.dwivna.ekaasaa.data.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfiguration {

    @Bean
    public ReactiveRedisTemplate<String, ?> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {
        RedisSerializationContext.RedisSerializationContextBuilder<String, ?> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
        RedisSerializationContext<String, ?> context = builder.build();
        log.info("Creating Bean {}...", "reactiveRedisTemplate");
        return new ReactiveRedisTemplate<>(factory, context);
    }

    @Bean
    public ReactiveRedisTemplate<String, User> reactiveUserRedisTemplate(ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<User> serializer = new Jackson2JsonRedisSerializer<>(User.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, User> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
        RedisSerializationContext<String, User> context = builder.value(serializer).build();
        log.info("Creating Bean {}...", "reactiveUserRedisTemplate");
        return new ReactiveRedisTemplate<>(factory, context);
    }

    @Bean
    public ReactiveValueOperations<String, User> redisValueUserOperations(ReactiveRedisTemplate<String, User> reactiveUserRedisTemplate) {
        return reactiveUserRedisTemplate.opsForValue();
    }

    @Bean
    public ChannelTopic channelTopic(@Value("${user.topic}") String topic) {
        log.info("Creating Bean channelTopic for topic {}...", topic);
        return new ChannelTopic(topic);
    }

    @Bean
    public ReactiveRedisMessageListenerContainer reactiveMsgListenerContainer(@Value("${user.topic}") String topic, ReactiveRedisConnectionFactory factory) {
        ReactiveRedisMessageListenerContainer container = new ReactiveRedisMessageListenerContainer(factory);
        container.receive(channelTopic(topic));
        log.info("Creating Bean reactiveMsgListenerContainer for topic {}...", topic);
        return container;
    }
}
