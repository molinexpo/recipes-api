package com.cmp.recipes_api.service;


import com.cmp.recipes_api.dto.RecipeDto;
import com.cmp.recipes_api.enums.Difficulty;
import com.cmp.recipes_api.enums.RecipeType;
import com.cmp.recipes_api.exception.RecipeNotFoundException;
import com.cmp.recipes_api.model.Recipe;
import com.cmp.recipes_api.repository.RecipeRepository;
import com.cmp.recipes_api.service.impl.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // arrange

    // execution

    // assertion

    //TODO add failing tests

    @Test
    public void testCreateRecipe() {
        // arrange
        Integer idRecipe = 3;
        Recipe recipe = createRecipe(idRecipe);
        RecipeDto recipeDto = createRecipeDto();

        when(modelMapper.map(recipeDto, Recipe.class)).thenReturn(mock(Recipe.class));
        when(recipeRepository.save(any(Recipe.class))).thenReturn(recipe);

        // execution
        Integer idSavedRecipe = recipeService.createRecipe(recipeDto);

        // assertion
        assertThat(idSavedRecipe).isEqualTo(idRecipe);
    }


    @Test
    public void testDeleteRecipe() {
        // arrange
        Integer idRecipe = 3;

        // execution
        recipeService.deleteRecipe(idRecipe);

        // assertion
        verify(recipeRepository, times(1)).deleteById(idRecipe);
    }

    @Test
    public void testGetRecipeById() throws RecipeNotFoundException {
        // arrange
        Integer idRecipe = 3;
        Recipe recipe = createRecipe(idRecipe);
        RecipeDto recipeDtoAfterSaved = createRecipeDtoAfterSaved(idRecipe);

        when(modelMapper.map(recipe, RecipeDto.class)).thenReturn(recipeDtoAfterSaved);

        // execution
        RecipeDto recipeDto = recipeService.getRecipeById(idRecipe);

        // assertion
        assertThat(recipeDto.getId()).isEqualTo(idRecipe);
    }

    private Recipe createRecipe(Integer idRecipe) {
        Recipe recipe = new Recipe();
        recipe.setId(idRecipe);
        recipe.setName("Pasta con tomate");
        recipe.setType(RecipeType.LUNCH);
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setNumberOfPeople(4);
        recipe.setDuration(30);
        recipe.setCreationDate(new Date());
        return recipe;
    }

    private RecipeDto createRecipeDto() {
        RecipeDto recipe = new RecipeDto();
        recipe.setName("Pasta con tomate");
        recipe.setType(RecipeType.LUNCH);
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setNumberOfPeople(4);
        recipe.setDuration(30);
        return recipe;
    }

    //TODO: Refactor me
    private RecipeDto createRecipeDtoAfterSaved(Integer idRecipe) {
        RecipeDto recipe = new RecipeDto();
        recipe.setId(idRecipe);
        recipe.setName("Pasta con tomate");
        recipe.setType(RecipeType.LUNCH);
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setNumberOfPeople(4);
        recipe.setDuration(30);
        return recipe;
    }

    @Test
    public void testRefactorMethod_NullRecipe() {
        // Caso: receta es null
        List<String> result = recipeService.refactorMethod(null);
        assertNotNull(result);
        assertEquals(0, result.size(), "La lista debería estar vacía cuando la receta es null");
    }

    @Test
    public void testRefactorMethod_EmptyIngredients() {
        // Caso: receta con ingredientes vacíos
        Recipe recipe = new Recipe();
        recipe.setIngredients(new ArrayList<>());

        List<String> result = recipeService.refactorMethod(recipe);
        assertNotNull(result);
        assertEquals(0, result.size(), "La lista debería estar vacía cuando no hay ingredientes");
    }

    @Test
    public void testRefactorMethod_NoIngredientsWithA() {
        // Caso: receta con ingredientes que no contienen "a"
        Recipe recipe = new Recipe();
        recipe.setIngredients(Arrays.asList("Cebollino", "Pimiento"));

        List<String> result = recipeService.refactorMethod(recipe);
        assertNotNull(result);
        assertEquals(0, result.size(), "La lista debería estar vacía cuando no hay ingredientes con 'a'");
    }

    @Test
    public void testRefactorMethod_IngredientsWithA() {
        // Caso: receta con algunos ingredientes que contienen "a"
        Recipe recipe = new Recipe();
        recipe.setIngredients(Arrays.asList("Manzana", "Tomate", "Banana", "Cebollino"));

        List<String> result = recipeService.refactorMethod(recipe);
        assertNotNull(result);
        assertEquals(3, result.size(), "La lista debería contener 3 ingredientes que contienen 'a'");
        assertEquals(Arrays.asList("Manzana", "Tomate", "Banana"), result);
    }
}
