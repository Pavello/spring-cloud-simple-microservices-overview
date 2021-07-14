package io.github.pavello.microservices.cloud.samples;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAsync
public class ManualTraceSample {

    @Autowired
	UnitOfWork unitOfWork;

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

