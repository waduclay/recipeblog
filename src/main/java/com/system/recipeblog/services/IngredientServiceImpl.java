package com.system.recipeblog.services;

import com.system.recipeblog.models.Ingredient;
import com.system.recipeblog.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IngredientServiceImpl implements IngredientService{
    private IngredientRepository ingredientRepository;

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
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
    public Ingredient updateIngredient(Ingredient ingredient, Long id) {
        Ingredient ingredient1 = ingredientRepository.findById(id).get();
        if(id==null){
            System.out.println("Ingredient does not exist");
        }else{
            ingredient1.setTitle(ingredient.getTitle());
            ingredient1.setAmount(ingredient.getAmount());

        } return ingredientRepository.save(ingredient1);
    }

    @Override
    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }
}
