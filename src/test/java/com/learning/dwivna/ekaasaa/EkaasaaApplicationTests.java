package com.learning.dwivna.ekaasaa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.listener.ReactiveRedisMessageListenerContainer;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class EkaasaaApplicationTests {

	@MockBean(name = "reactiveRedisConnectionFactory")
	private ReactiveRedisConnectionFactory reactiveRedisConnectionFactory;

	// The production listener bean calls receive during initialization.
	@MockBean(name = "reactiveMsgListenerContainer")
	private ReactiveRedisMessageListenerContainer reactiveMsgListenerContainer;

	@Test
	void contextLoads() {
	}

}
