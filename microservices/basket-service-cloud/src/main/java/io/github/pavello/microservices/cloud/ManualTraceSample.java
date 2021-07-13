package io.github.pavello.microservices.cloud;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.pavello.microservices.cloud.samples.UnitOfWork;

@RestController
@SpringBootApplication(scanBasePackages = "pl.softwareskill.course.microservices.basket.config")
@EnableAsync
@Import(Config.class)
public class ManualTraceSample {

    @Autowired
	UnitOfWork unitOfWork;

    /**
     * Run with -Dspring.profiles.active=tracing
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ManualTraceSample.class, args);
    }

    @GetMapping("/test2")
    public String test2() throws InterruptedException {
        unitOfWork.doSomeWork(UUID.randomUUID().toString());
        return "test";
    }

    @GetMapping("/test3")
    public String test3() throws InterruptedException {
        unitOfWork.doSomeWorkAsync(UUID.randomUUID().toString());
        return "test";
    }
}

@Configuration
class Config {

    @Bean
    public UnitOfWork unitOfWork() {
        return new UnitOfWork();
    }
}