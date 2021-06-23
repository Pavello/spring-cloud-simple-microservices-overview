package io.github.pavello.basketservice.config;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("orders.service")
public class OrdersServiceProperties {

	String url;
	Duration timeout;
}
