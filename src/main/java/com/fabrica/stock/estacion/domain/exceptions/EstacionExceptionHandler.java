package com.fabrica.stock.estacion.domain.exceptions;

import com.fabrica.stock.shared.domain.DomainErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EstacionExceptionHandler {
    @ExceptionHandler(EstacionNoEncontradaException.class)
    public ResponseEntity<DomainErrorResponse> manejarEstacionNoEncontrada(EstacionNoEncontradaException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }
}