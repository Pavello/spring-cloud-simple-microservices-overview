package io.github.pavello.orderscloud.domain;

import java.util.UUID;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository repository;

	public Order createOrder(String description) {
		UUID orderId = UUID.randomUUID();
		Order createdOrder = Order.create(orderId, description);

		repository.save(createdOrder);

		return createdOrder;

	}
}
