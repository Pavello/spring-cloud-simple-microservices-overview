package io.github.pavello.ordersservice.infrastructure;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import io.github.pavello.ordersservice.domain.Order;
import io.github.pavello.ordersservice.domain.OrderRepository;

public class InMemoryOrderRepository implements OrderRepository {

	private final Map<UUID, Order> orders = new ConcurrentHashMap<>();

	@Override
	public Optional<Order> find(final UUID id) {
		return Optional.ofNullable(orders.get(id));
	}

	@Override
	public void save(final Order order) {
		orders.put(order.getId(), order);
	}

	@Override
	public void remove(final UUID id) {
		orders.remove(id);
	}
}
