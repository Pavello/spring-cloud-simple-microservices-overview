package io.github.pavello.basketservice.service;

import java.util.Objects;
import java.util.UUID;

import org.springframework.web.client.RestTemplate;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class OrdersService {

	private final RestTemplate restTemplate;
	private final String baseUrl;

	public UUID createOrder(String description) {
		Request request = Request.builder().description(description).build();

		return Objects.requireNonNull(
				restTemplate.postForEntity(baseUrl + "/orders", request, Response.class
				).getBody()).getOrderId();
	}

	@Data
	@Builder
	private static class Request {
		private String description;
	}

	@Data
	private static class Response {
		private UUID orderId;
	}
}
