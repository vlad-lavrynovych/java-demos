package com.java_demo.feign;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignApplication.class, args);
	}

	@Bean
	public CommandLineRunner feignRequest(IngredientClient client) {
		return args -> {
			try {
				System.out.println(client.getIngredients());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		};
	}
}
