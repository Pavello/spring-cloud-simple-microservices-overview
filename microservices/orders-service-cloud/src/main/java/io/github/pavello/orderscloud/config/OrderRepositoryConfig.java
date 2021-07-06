package io.github.pavello.orderscloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.pavello.orderscloud.domain.OrderRepository;
import io.github.pavello.orderscloud.infrastructure.InMemoryOrderRepository;

@Configuration
public class OrderRepositoryConfig {

	@Bean
	public OrderRepository repository() {
		return new InMemoryOrderRepository();
	}
}
