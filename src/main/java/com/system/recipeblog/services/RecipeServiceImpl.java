package com.system.recipeblog.services;

import com.system.recipeblog.models.Category;
import com.system.recipeblog.models.Ingredient;
import com.system.recipeblog.models.Recipe;
import com.system.recipeblog.repositories.CategoryRepository;
import com.system.recipeblog.repositories.IngredientRepository;
import com.system.recipeblog.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService{
    private RecipeRepository recipeRepository;
    private Recipe recipe;
    private CategoryRepository categoryRepository;
    private IngredientRepository ingredientRepository;

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
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes;
    }

    @Override
    public Optional<Recipe> findById(Long id) {
        if(!recipeRepository.findById(recipe.getId()).isPresent()){
            System.out.println("Recipe not found");
        }return Optional.ofNullable(recipe);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Set<Category> categories, Set<Ingredient> ingredients, Long id) {
        Recipe recipe1 = recipeRepository.findById(id).get();
        if(id == null){
            System.out.println("Recipe does not exist");
        } else {
            recipe1.setTitle(recipe.getTitle());
            recipe1.setDescription(recipe.getDescription());
            recipe1.setServings(recipe.getServings());
            recipe1.setCookTime(recipe.getCookTime());
            recipe1.setPrepTime(recipe.getPrepTime());
            recipe1.setCategories(categories);
            recipe1.setIngredients(ingredients);
        } return recipeRepository.save(recipe1);
    }

    @Override
    public void deleteRecipe(Long id) {

        if(recipeRepository.findById(recipe.getId()).isPresent()){
            recipeRepository.deleteById(id);
        }else{
            System.out.println("Recipe not found");
        } System.out.println("Recipe has been successfully deleted");
    }
}
