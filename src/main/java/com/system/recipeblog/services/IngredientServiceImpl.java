package com.system.recipeblog.services;

import com.system.recipeblog.models.Ingredient;
import com.system.recipeblog.models.Recipe;
import com.system.recipeblog.repositories.IngredientRepository;
import com.system.recipeblog.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService{
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    @Override
    public Ingredient saveOrUpdateIngredient(Ingredient ingredient, Recipe recipe) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipe.getId());

        if (!recipeOptional.isPresent()) {
            System.out.println("Recipe not found");
            return null; // Or throw an exception if necessary
        }

        recipe = recipeOptional.get();

        Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream().findFirst();

        if (ingredientOptional.isPresent()) {
            Ingredient ingredientFound = ingredientOptional.get();
            ingredientFound.setTitle(ingredient.getTitle());
            ingredientFound.setAmount(ingredient.getAmount());
        } else {
            ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);
        }

        Recipe savedRecipe = recipeRepository.save(recipe);
        return ingredientRepository.save(ingredient);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Optional<Ingredient> findById(Long id) {
        return ingredientRepository.findById(id);
    }

    @Override
    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }
}
