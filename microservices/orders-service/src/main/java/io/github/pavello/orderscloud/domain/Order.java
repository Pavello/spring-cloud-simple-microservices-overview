package io.github.pavello.orderscloud.domain;

import java.util.UUID;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Order {

	private UUID id;
	private String description;

	Order(final UUID id, final String description) {
		this.id = id;
		this.description = description;
	}

	public static Order create(UUID id, String description) {
		return new Order(id, description);
	}
}
