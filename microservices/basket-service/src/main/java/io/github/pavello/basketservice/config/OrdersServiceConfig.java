package io.github.pavello.basketservice.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import io.github.pavello.basketservice.service.OrdersService;

@Configuration
@EnableConfigurationProperties(OrdersServiceProperties.class)
public class OrdersServiceConfig {

	@Bean
	public OrdersService createOrdersService(OrdersServiceProperties config) {
		return new OrdersService(getRestTemplate(config), config.getUrl());
	}

	public RestTemplate getRestTemplate(OrdersServiceProperties config) {
		var factory = new SimpleClientHttpRequestFactory();

		factory.setConnectTimeout((int) config.getTimeout().toMillis());
		factory.setReadTimeout((int) config.getTimeout().toMillis());

		return new RestTemplate(factory);
	}

}
