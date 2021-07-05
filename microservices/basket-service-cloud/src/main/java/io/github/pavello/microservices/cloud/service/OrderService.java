package io.github.pavello.microservices.cloud.service;

import java.time.Duration;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class OrderService {

	private final WebClient.Builder webClient;
	private final Duration timeout;

	public Mono<UUID> createOrder(String description) {
		Request request = Request.builder()
				.description(description)
				.build();

		return webClient
				.filters(exchangeFilterFunctions ->
					exchangeFilterFunctions.add(logRequest())
				)
				.build()
				.post()
				.uri("http://order-service/orders")
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(request))
				.retrieve()
				.bodyToMono(Response.class)
				.map(Response::getOrderId)
				.timeout(timeout);
	}

	ExchangeFilterFunction logRequest() {
		return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
			if (log.isDebugEnabled()) {
				StringBuilder sb = new StringBuilder("Request: \n");
				//append clientRequest method and url
				clientRequest
						.headers();
				log.debug(sb.toString());
			}
			return Mono.just(clientRequest);
		});
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
