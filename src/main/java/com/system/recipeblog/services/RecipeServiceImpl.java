package com.system.recipeblog.services;

import com.system.recipeblog.exception.ex.RecipeNotFoundException;
import com.system.recipeblog.models.Category;
import com.system.recipeblog.models.Ingredient;
import com.system.recipeblog.models.Recipe;
import com.system.recipeblog.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

import static com.system.recipeblog.util.Constants.RECIPE_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;


    @Override
    public Recipe saveRecipe(Recipe recipe, Set<Category> categories, Set<Ingredient> ingredients) {

        recipe.setTitle(recipe.getTitle());
        recipe.setDescription(recipe.getDescription());
        recipe.setServings(recipe.getServings());
        recipe.setCookTime(recipe.getCookTime());
        recipe.setPrepTime(recipe.getPrepTime());
        recipe.setCategories(categories);
        recipe.setIngredients(ingredients);
        return recipeRepository.save(recipe);

    }

    @Override
    public List<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(String.format(RECIPE_NOT_FOUND, id)));
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Set<Category> categories, Set<Ingredient> ingredients, Long id) {
        Recipe recipe1 = recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(String.format(RECIPE_NOT_FOUND, id)));

            recipe1.setTitle(recipe.getTitle());
            recipe1.setDescription(recipe.getDescription());
            recipe1.setServings(recipe.getServings());
            recipe1.setCookTime(recipe.getCookTime());
            recipe1.setPrepTime(recipe.getPrepTime());
            recipe1.setCategories(categories);
            recipe1.setIngredients(ingredients);

        return recipeRepository.save(recipe1);
    }

    @Override
    public void deleteRecipe(Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new RecipeNotFoundException(String.format(RECIPE_NOT_FOUND, id));
        }
        recipeRepository.deleteById(id);
    }
}
