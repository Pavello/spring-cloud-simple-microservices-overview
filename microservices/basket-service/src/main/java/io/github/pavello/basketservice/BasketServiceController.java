package io.github.pavello.basketservice;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.pavello.basketservice.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketServiceController {

	private final OrdersService ordersService;

	@GetMapping
	public String createOrder() {
		log.info("Creating order");

		try {
			UUID orderId = ordersService.createOrder("Some order");

			return "Created order id: {}" + orderId;
		} catch (Exception ex) {
			log.error("Error during creating order", ex);

			return "Error";
		}
	}

}
