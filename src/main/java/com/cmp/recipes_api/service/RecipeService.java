package com.cmp.recipes_api.service;

import com.cmp.recipes_api.dto.RecipeDto;

public interface RecipeService {

    Integer createRecipe(RecipeDto recipe);

    void deleteRecipe(Integer id);

    RecipeDto getRecipeById(Integer id) throws Exception;

    void updateRecipe(RecipeDto recipeDto);

}
