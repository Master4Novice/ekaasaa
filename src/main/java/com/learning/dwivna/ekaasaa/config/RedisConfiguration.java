package com.learning.dwivna.ekaasaa.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfiguration implements MasterRedis<String, String> {

    @Value("${spring.data.redis.host:localhost}")
    private String redisHost;

    @Value("${spring.data.redis.port:6379}")
    private int redisPort;

    @Value("${spring.data.redis.password:}")
    private String redisPassword;

    @Bean("reactiveRedisConnectionFactory")
    public ReactiveRedisConnectionFactory factory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisHost, redisPort);
        if (!redisPassword.isEmpty()) {
            configuration.setPassword(redisPassword);
        }
        return new LettuceConnectionFactory(configuration);
    }

    @Override
    @Bean("reactiveRedisTemplate")
    public ReactiveRedisTemplate<String, String> reactiveMasterRedisTemplate(@Qualifier("reactiveRedisConnectionFactory") ReactiveRedisConnectionFactory factory) {
        RedisSerializationContext.RedisSerializationContextBuilder<String, String> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
        RedisSerializationContext<String, String> context = builder.build();
        log.info("Creating Bean {}...", "reactiveRedisTemplate");
        return new ReactiveRedisTemplate<>(factory, context);
    }

    @Bean
    public ChannelTopic channelTopic(@Value("${user.topic}") String topic) {
        log.info("Creating Bean channelTopic for topic {}...", topic);
        return new ChannelTopic(topic);
    }

    @Bean
    public ReactiveRedisMessageListenerContainer reactiveMsgListenerContainer(@Value("${user.topic}") String topic, @Qualifier("reactiveRedisConnectionFactory") ReactiveRedisConnectionFactory factory) {
        ReactiveRedisMessageListenerContainer container = new ReactiveRedisMessageListenerContainer(factory);
        container.receive(channelTopic(topic));
        log.info("Creating Bean reactiveMsgListenerContainer for topic {}...", topic);
        return container;
    }
}
