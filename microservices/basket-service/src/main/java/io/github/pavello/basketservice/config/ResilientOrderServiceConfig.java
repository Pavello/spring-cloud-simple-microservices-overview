package io.github.pavello.basketservice.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.pavello.basketservice.service.OrdersService;
import io.github.pavello.basketservice.service.ResilientOrderService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ResilientOrderServiceConfig {

	@Bean
	public ResilientOrderService resilientOrderService(OrdersService orderService) {
		return new ResilientOrderService(circuitBreaker(), orderService, retry());
	}

	private Retry retry() {
		Retry retry = Retry.of("create order", RetryConfig.custom()
				.maxAttempts(3)
				.waitDuration(Duration.ofMillis(200))
				.build());

		retry.getEventPublisher()
				.onEvent(event -> log.info("Retry event: {}", event));

		return retry;
	}

	private Retry noRetry() {
		return Retry.of("create order", RetryConfig.custom()
				.maxAttempts(1)
				.build());
	}

	private CircuitBreaker circuitBreaker() {
		CircuitBreaker circuitBreaker = CircuitBreaker.of("create order", CircuitBreakerConfig.custom()
				.failureRateThreshold(50)
				.minimumNumberOfCalls(3)
				.slidingWindowSize(3)
				.slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
				.waitDurationInOpenState(Duration.ofSeconds(5))
				.permittedNumberOfCallsInHalfOpenState(1)
				.build());

		circuitBreaker.getEventPublisher()
				.onEvent(event -> log.info("Circuit Breaker event: {}", event));

		return circuitBreaker;
	}
}
