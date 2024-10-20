package com.cmp.recipes_api.service.impl;

import com.cmp.recipes_api.dto.RecipeDto;
import com.cmp.recipes_api.exception.RecipeNotFoundException;
import com.cmp.recipes_api.model.Recipe;
import com.cmp.recipes_api.repository.RecipeRepository;
import com.cmp.recipes_api.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final ModelMapper modelMapper;

    @Override
    public Integer createRecipe(RecipeDto recipe) {
        Recipe recipeToSave = modelMapper.map(recipe, Recipe.class);
        recipeToSave.setCreationDate(new Date());
        return recipeRepository.save(recipeToSave).getId();
    }

    @Override
    public void deleteRecipe(Integer id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public RecipeDto getRecipeById(Integer id) {
        log.info("Getting recipe with id: {}", id);
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException("Recipe not found"));
        log.info("Returning recipe with id: {}", id);
        return modelMapper.map(recipe, RecipeDto.class);
    }

    @Override
    public List<RecipeDto> getRecipes() {
        log.info("Getting all recipes");
        List<Recipe> recipes = recipeRepository.findAll();
        log.info("Returning all recipes");
        return recipes.stream().map(recipe -> modelMapper.map(recipe, RecipeDto.class)).toList();
    }

    @Override
    public void updateRecipe(RecipeDto recipeDto) {
        recipeRepository.save(modelMapper.map(recipeDto, Recipe.class));
    }
}
