package com.learning.dwivna.ekaasaa.service.impl;

import com.learning.dwivna.ekaasaa.service.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    @Autowired
    private ChannelTopic channelTopic;

    @Override
    public void publish(String message) {
        log.info("{} for the topic {}", message, channelTopic.getTopic());
        reactiveRedisTemplate.convertAndSend(this.channelTopic.getTopic(), message).subscribe();
    }
}
