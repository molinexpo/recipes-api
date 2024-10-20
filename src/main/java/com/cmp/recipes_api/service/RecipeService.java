package com.cmp.recipes_api.service;

import com.cmp.recipes_api.dto.RecipeDto;

import java.util.List;

public interface RecipeService {

    Integer createRecipe(RecipeDto recipe);

    void deleteRecipe(Integer id);

    RecipeDto getRecipeById(Integer id);

    List<RecipeDto> getRecipes();

    void updateRecipe(RecipeDto recipeDto);

}
