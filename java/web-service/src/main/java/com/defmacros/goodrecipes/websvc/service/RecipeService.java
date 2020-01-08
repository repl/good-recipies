package com.defmacros.goodrecipes.websvc.service;

import java.util.List;
import java.util.stream.Collectors;

import com.defmacros.goodrecipes.websvc.dto.Recipe;
import com.defmacros.goodrecipes.websvc.exception.ServiceException;
import com.defmacros.goodrecipes.websvc.model.Ingredient;
import com.defmacros.goodrecipes.websvc.payload.RecipeCreateRequest;
import com.defmacros.goodrecipes.websvc.repository.RecipeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe createRecipe(RecipeCreateRequest input) throws ServiceException {
        com.defmacros.goodrecipes.websvc.model.Recipe recipe = new com.defmacros.goodrecipes.websvc.model.Recipe();
        recipe.setTitle(input.getTitle());
        recipe.setInstructions(input.getInstructions());
        if (input.getIngredients() != null) {
            List<Ingredient> ingredients = input.getIngredients().stream().map(in -> {
                    Ingredient ing = new Ingredient();
                    ing.setEntry(in);
                    ing.setRecipe(recipe);
                    return ing;
                }).collect(Collectors.toList());
            recipe.setIngredients(ingredients);
        }
        com.defmacros.goodrecipes.websvc.model.Recipe recipeSaved = recipeRepository.save(recipe);

        Recipe recipeDto = new Recipe();
        recipeDto.setId(recipeSaved.getId());
        recipeDto.setUid(recipeSaved.getUid());
        recipeDto.setTitle(recipeSaved.getTitle());
        if (recipeSaved.getIngredients() != null) {
            recipeDto.setIngredients(
                    recipeSaved.getIngredients().stream().map(in -> in.getEntry()).collect(Collectors.toList()));
        }
        return recipeDto;
    }
}
