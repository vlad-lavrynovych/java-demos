package com.java_demos.reactor.controller;

import com.java_demos.reactor.domain.Ingredient;
import com.java_demos.reactor.repository.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import java.net.URI;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@Slf4j
public class RouterFunctionConfig {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Bean
    public RouterFunction<?> helloRouterFunction() {
        return route(GET("/hello"),
                request -> ServerResponse.ok().body(Mono.just("Hello World!"), String.class));
    }

    @Bean
    public RouterFunction<?> routerFunction() {
        return route(
                GET("/ingredients"),
                request -> {
                    log.info("Received request " + request);
                    return ServerResponse.ok().body(
                            ingredientRepository.findAll().take(12),
                            Ingredient.class);
                }
        );
    }

    public Mono<ServerResponse> postIngredient(ServerRequest request) {
        Mono<Ingredient> ingredient = request.bodyToMono(Ingredient.class);
        Mono<Ingredient> savedIngredient = ingredientRepository.save(ingredient);
        return ServerResponse
                .created(URI.create(
                        "http://localhost:8083/ingredients"))
                .body(savedIngredient, Ingredient.class);
    }
}
