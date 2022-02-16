package com.java_demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient("reactor")
public interface IngredientClient {

    @GetMapping("/ingredients")
    List<Ingredient> getIngredients();
}
