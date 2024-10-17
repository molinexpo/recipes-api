package com.cmp.recipes_api.controller;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

// record hace que el objeto sea inmutable
// lo que quiere decir que una vez se crea la instancia no puede ni va a cambiar
public record ApiErrorResponse(
        HttpStatus status,
        LocalDateTime date,
        String errorMessage
) {
}
