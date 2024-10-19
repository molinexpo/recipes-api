package com.cmp.recipes_api.controller;

import com.cmp.recipes_api.dto.RecipeDto;
import com.cmp.recipes_api.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping("")
    public ResponseEntity<Map<String, Integer>> createRecipe(@RequestBody RecipeDto recipeDto) {
        int id = recipeService.createRecipe(recipeDto);
        Map<String, Integer> response = new HashMap<>();
        response.put("id", id);  // Wrap the integer in a JSON object with the key "id"
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Integer id) {
        recipeService.deleteRecipe(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipe(Integer id) throws Exception {
        return new ResponseEntity<>(recipeService.getRecipeById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRecipe(@RequestBody RecipeDto recipeDto) {
        recipeService.updateRecipe(recipeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
