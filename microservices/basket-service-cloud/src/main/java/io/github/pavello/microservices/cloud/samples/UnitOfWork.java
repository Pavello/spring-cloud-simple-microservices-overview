package io.github.pavello.microservices.cloud.samples;

import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.scheduling.annotation.Async;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnitOfWork {
	@SneakyThrows
	@NewSpan
	public void doSomeWork(@SpanTag("argument") String argument) {
		log.info("Doing work {}", argument);
		Thread.sleep(1000);
	}

	@SneakyThrows
	@Async
	public void doSomeWorkAsync(@SpanTag("argument") String argument) {
		log.info("Doing work {}", argument);
		Thread.sleep(1000);
	}

}
