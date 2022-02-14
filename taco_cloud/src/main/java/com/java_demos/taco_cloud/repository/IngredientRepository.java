package com.java_demos.taco_cloud.repository;

import com.java_demos.taco_cloud.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

    List<Ingredient> findAll();

    Ingredient getById(String id);

    Ingredient save(Ingredient ingredient);
}
