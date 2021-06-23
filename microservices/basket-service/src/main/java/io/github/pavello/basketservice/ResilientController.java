package io.github.pavello.basketservice;

import io.github.pavello.basketservice.service.ResilientOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/resilient")
@RequiredArgsConstructor
public class ResilientController {

	private final ResilientOrderService orderService;

	@GetMapping
	public String createOrder() {
		log.info("Creating order");

		Optional<UUID> createdOrderId = orderService.createOrder("Some order");

		if(createdOrderId.isPresent()) {
			log.info("Created order id: {}", createdOrderId);
			return "Created order: " + createdOrderId;
		} else {
			return "Order cannot be created at this moment";
		}
	}
}
