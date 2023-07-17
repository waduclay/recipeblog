package com.system.recipeblog.services;

import com.system.recipeblog.models.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
    Ingredient saveIngredient(Ingredient ingredient);
    List<Ingredient> getAllIngredients();
    Optional<Ingredient> findById(Long id);
    Ingredient updateIngredient(Ingredient ingredient, Long id);
    void deleteIngredient(Long id);
}
