package com.java_demos.reactor;

import com.java_demos.reactor.domain.Ingredient;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@SpringBootApplication
@Slf4j
@EnableEurekaClient
public class ReactorApplication {

    @Autowired
    private DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(ReactorApplication.class, args);
    }

    @Bean
    ConnectionFactoryInitializer initializer(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql"), new ClassPathResource("data.sql")));

        return initializer;
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public CommandLineRunner demo() {

        return (args) -> {
            Flux<Ingredient> ingredients = WebClient.create()
                    .get()
                    .uri("http://localhost:8083/ingredients")
                    .retrieve()
                    .bodyToFlux(Ingredient.class);
            log.error("Extracted");
            ingredients.subscribe(s -> log.error(s.getName()));
        };
    }


    @Bean
    public CommandLineRunner demo(@LoadBalanced WebClient.Builder webClientBuilder) {

        discoveryClient.getInstances("reactor").forEach((ServiceInstance s) -> {
            System.out.println(ToStringBuilder.reflectionToString(s));
        });

        return (args) -> {
            Flux<Ingredient> ingredients = webClientBuilder.build()
                    .get()
                    .uri("http://reactor/ingredients")
                    .retrieve()
                    .bodyToFlux(Ingredient.class);
            log.error("Extracted");
            ingredients.subscribe(s -> log.error(s.getName()));
        };
    }
}
