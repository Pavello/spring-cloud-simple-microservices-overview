package io.github.pavello.ordersservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.pavello.ordersservice.domain.OrderRepository;
import io.github.pavello.ordersservice.infrastructure.InMemoryOrderRepository;

@Configuration
public class OrderRepositoryConfig {

	@Bean
	public OrderRepository repository() {
		return new InMemoryOrderRepository();
	}
}
