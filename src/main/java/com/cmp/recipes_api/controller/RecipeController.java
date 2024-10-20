package com.cmp.recipes_api.controller;

import com.cmp.recipes_api.dto.RecipeDto;
import com.cmp.recipes_api.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<Integer> createRecipe(@RequestBody RecipeDto recipeDto) {
        return new ResponseEntity<>(recipeService.createRecipe(recipeDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Integer id) {
        recipeService.deleteRecipe(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<RecipeDto>> getRecipes() {
        return new ResponseEntity<>(recipeService.getRecipes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipeById(@PathVariable Integer id) {
        return new ResponseEntity<>(recipeService.getRecipeById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRecipe(@RequestBody RecipeDto recipeDto) {
        recipeService.updateRecipe(recipeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
