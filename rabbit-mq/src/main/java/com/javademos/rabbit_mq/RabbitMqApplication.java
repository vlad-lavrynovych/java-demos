package com.javademos.rabbit_mq;

import com.javademos.rabbit_mq.dto.Order;
import com.javademos.rabbit_mq.service.RabbitMessagingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class RabbitMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(RabbitMessagingService messagingService) {
        log.info("\n====== Sending message...");
        return args -> messagingService.sendOrder(new Order(1, "TEST_ORDER"));
    }
}
