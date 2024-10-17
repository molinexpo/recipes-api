package com.cmp.recipes_api.controller;

import com.cmp.recipes_api.exception.RecipeNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class ApiControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecipeNotFoundException.class)
    protected ResponseEntity<ApiErrorResponse> handleNotFoundException(RecipeNotFoundException ex) {
        log.error(ex.getMessage());
        ApiErrorResponse response = new ApiErrorResponse(HttpStatus.NOT_FOUND, LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity<>(response, response.status());
    }
}
