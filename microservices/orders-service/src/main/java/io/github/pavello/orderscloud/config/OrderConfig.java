package io.github.pavello.orderscloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.pavello.orderscloud.domain.OrderRepository;
import io.github.pavello.orderscloud.domain.OrderService;

@Configuration
public class OrderConfig {

	@Bean
	public OrderService createOrderService(OrderRepository orderRepository) {
		return new OrderService(orderRepository);
	}

}
