package io.github.pavello.microservices.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.pavello.microservices.cloud.samples.UnitOfWork;

@Configuration
public class TracingConfig {

	@Bean
	public UnitOfWork unitOfWork() {
		return new UnitOfWork();
	}
}
