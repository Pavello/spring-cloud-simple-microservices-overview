package io.github.pavello.microservices.cloud;

import io.github.pavello.microservices.cloud.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@SpringBootApplication(scanBasePackages = "io.github.pavello.microservices.cloud.config")
public class BasketServiceCloudApplication {

	private final OrderService orderService;

	/**
	 * Run application without profiles or with
	 * -Dspring.profiles.active=consul
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(BasketServiceCloudApplication.class, args);
	}

	@GetMapping("/simple")
	public Mono<String> createOrder() {
		log.info("Creating order");

		return orderService.createOrder("Some order")
				.doOnNext(createdOrderId -> log.info("Created order id: {}", createdOrderId))
				.map(UUID::toString);
	}
}
