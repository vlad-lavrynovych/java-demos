package com.java_demos.reactor.repository;


import com.java_demos.reactor.domain.Ingredient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IngredientRepository extends ReactiveCrudRepository<Ingredient, String> {

    Flux<Ingredient> findAll();

    Mono<Ingredient> getById(String id);

    Mono<Ingredient> save(Mono<Ingredient> ingredient);
}
