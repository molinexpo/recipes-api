package com.cmp.recipes_api.exception;

public class RecipeNotFoundException extends RuntimeException {

    public RecipeNotFoundException (String errorMessage) {
        super(errorMessage);
    }

}
