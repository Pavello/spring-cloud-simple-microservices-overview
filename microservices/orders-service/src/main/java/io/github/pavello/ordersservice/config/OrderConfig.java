package io.github.pavello.ordersservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.pavello.ordersservice.domain.OrderRepository;
import io.github.pavello.ordersservice.domain.OrderService;

@Configuration
public class OrderConfig {

	@Bean
	public OrderService createOrderService(OrderRepository orderRepository) {
		return new OrderService(orderRepository);
	}

}
