package com.java_demos.taco_cloud.controller;

import com.java_demos.taco_cloud.domain.Ingredient;
import com.java_demos.taco_cloud.repository.IngredientRepository;
import com.java_demos.taco_cloud.repository.TacoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.java_demos.taco_cloud.domain.Ingredient.Type;
import com.java_demos.taco_cloud.domain.Order;
import com.java_demos.taco_cloud.domain.Taco;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    private TacoRepository tacoRepository;

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {

        List<Ingredient> ingredients = new ArrayList<>(ingredientRepository.findAll());

        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(
                    type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(
            @Valid Taco design,
            Errors errors,
            @ModelAttribute Order order) {

        if (errors.hasErrors()) {
            log.error(errors.getAllErrors().toString());
            return "design";
        }

        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);

        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }


    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(s -> s.getType().equals(type)).collect(Collectors.toList());
    }
}
