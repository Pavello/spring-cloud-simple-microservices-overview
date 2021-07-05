package io.github.pavello.microservices.cloud.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.pavello.microservices.cloud.service.OrderService;

@Configuration
public class OrderServiceConfig {

	@Bean
	public OrderService orderService(WebClient.Builder orderServiceWebClient, @Value("${order.service.timeout}") String timeout) {
		return new OrderService(orderServiceWebClient, Duration.parse(timeout));
	}
}
