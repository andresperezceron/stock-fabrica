package com.fabrica.stock.ubicacion.domain.exceptions;

import com.fabrica.stock.shared.response.DomainErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UbicacionExceptionHandler {
    @ExceptionHandler(UbicacionNoEncontradaException.class)
    public ResponseEntity<DomainErrorResponse> manejarUbicacionNoEncontradaException(UbicacionNoEncontradaException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(NombreUbicacionDuplicadoException.class)
    public ResponseEntity<DomainErrorResponse> manejarNombreUbicacionDuplicado(NombreUbicacionDuplicadoException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }
}