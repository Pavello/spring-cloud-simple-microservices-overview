package io.github.pavello.orderscloud.domain;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

	Optional<Order> find(UUID id);

	void save(Order order);

	void remove(UUID id);
}
