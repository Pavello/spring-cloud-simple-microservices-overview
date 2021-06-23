package io.github.pavello.basketservice.service;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.retry.Retry;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResilientOrderService {

	private final CircuitBreaker circuitBreaker;
	private final OrdersService ordersService;
	private final Retry retry;

	public Optional<UUID> createOrder(String description) {
		Supplier<Optional<UUID>> supplier = CircuitBreaker.decorateSupplier(circuitBreaker, () -> Optional.of(ordersService.createOrder(description)));
		supplier = Retry
				.decorateSupplier(retry, supplier);
		return Try.ofSupplier(supplier)
				.recover(throwable -> Optional.empty()).get();
	}
}
