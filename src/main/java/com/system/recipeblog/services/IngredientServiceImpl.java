package com.system.recipeblog.services;

import com.system.recipeblog.exception.ex.IngredientNotFoundException;
import com.system.recipeblog.exception.ex.RecipeNotFoundException;
import com.system.recipeblog.models.Ingredient;
import com.system.recipeblog.models.Recipe;
import com.system.recipeblog.repositories.IngredientRepository;
import com.system.recipeblog.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.system.recipeblog.util.Constants.INGREDIENT_NOT_FOUND;
import static com.system.recipeblog.util.Constants.RECIPE_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService{
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    @Override
    public Ingredient saveOrUpdateIngredient(Ingredient ingredient, Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(String.format(RECIPE_NOT_FOUND, recipeId)));


        Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream().findFirst();

        if (ingredientOptional.isPresent()) {
            Ingredient ingredientFound = ingredientOptional.get();
            ingredientFound.setTitle(ingredient.getTitle());
            ingredientFound.setAmount(ingredient.getAmount());
        } else {
            ingredient.setRecipe(recipe);
            recipe.addIngredient(ingredient);
        }

        recipeRepository.save(recipe);
        return ingredientRepository.save(ingredient);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient findById(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(String.format(INGREDIENT_NOT_FOUND, id)));
    }

    @Override
    public void deleteIngredient(Long id) {
        if (!ingredientRepository.existsById(id)) {
            throw new IngredientNotFoundException(String.format(INGREDIENT_NOT_FOUND, id));
        }
        ingredientRepository.deleteById(id);
    }
}
