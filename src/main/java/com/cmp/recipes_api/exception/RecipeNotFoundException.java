package com.cmp.recipes_api.exception;

public class RecipeNotFoundException extends Exception {

    public RecipeNotFoundException (String errorMessage) {
        super(errorMessage);
    }

}
