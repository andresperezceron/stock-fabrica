package com.fabrica.stock.familia.domain.exceptions;

import com.fabrica.stock.shared.response.DomainErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FamiliaExceptionHandler {
    @ExceptionHandler(FamiliaNoEncontradaException.class)
    public ResponseEntity<DomainErrorResponse> manejarFamiliaNoEncontrada(FamiliaNoEncontradaException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(NombreFamiliaDuplicadoException.class)
    public ResponseEntity<DomainErrorResponse> manejarNombreFamiliaDuplicado(NombreFamiliaDuplicadoException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }
}