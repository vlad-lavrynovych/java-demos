package com.java_demos.integration;

import com.java_demos.integration.integration.Order;
import com.java_demos.integration.properties.ApiProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Slf4j
public class IntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegrationApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(RestTemplate restTemplate, ApiProperties apiProperties) throws InterruptedException {
        log.info("\n====== Sending Order...");

        // pipeline: send to input channel on /send-order, process and send to another endpoint (/receive-order in controller)

        return args -> {

            restTemplate.postForObject(apiProperties.getSendUrl(), new Order("123", "123123"), Order.class);
        };
    }
}
