package io.github.pavello.ordersservice.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.pavello.ordersservice.domain.Order;
import io.github.pavello.ordersservice.domain.OrderService;
import io.github.pavello.ordersservice.rest.dto.CreateOrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static java.util.Collections.singletonMap;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class CreateOrderController {

	private final OrderService orderService;

	@PostMapping
	public ResponseEntity create(@RequestBody CreateOrderDto createOrder) {
		log.info("Creating order: {}", createOrder);
		Order createdOrder = orderService.createOrder(createOrder.getDescription());
		log.info("Created order: {}", createdOrder);
		return createResponse(createdOrder);
	}

	private ResponseEntity createResponse(Order createdOrder) {
		return ResponseEntity
				.accepted()
				.body(singletonMap("orderId", createdOrder.getId().toString()));
	}

}
