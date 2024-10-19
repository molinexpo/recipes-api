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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    //TODO: refactor me
    public List<String> refactorMethod(Recipe recipe) {
        List<String> newIngredients = new ArrayList<>();
        if(recipe != null) {
            if(recipe.getIngredients() != null && recipe.getIngredients().size() > 0) {
                for(int i = 0; i < recipe.getIngredients().size(); i++) {
                    if (recipe.getIngredients().get(i).contains("a")) {
                        newIngredients.add(recipe.getIngredients().get(i));
                    }
                }
            }
        }

        return newIngredients;
    }

    @Override
    public void deleteRecipe(Integer id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public RecipeDto getRecipeById(Integer id) throws RecipeNotFoundException {
        log.info("Getting recipe with id: {}", id);
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new RecipeNotFoundException("Recipe not found"));
        log.info("Returning recipe with id: {}", id);
        return modelMapper.map(recipe, RecipeDto.class);
    }

    @Override
    public void updateRecipe(RecipeDto recipeDto) {
        recipeRepository.save(modelMapper.map(recipeDto, Recipe.class));
    }
}
