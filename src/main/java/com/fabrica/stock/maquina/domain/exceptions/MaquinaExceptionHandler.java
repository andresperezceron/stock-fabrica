package com.fabrica.stock.maquina.domain.exceptions;

import com.fabrica.stock.shared.domain.DomainErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MaquinaExceptionHandler {

    @ExceptionHandler(NombreMaquinaNoValidoException.class)
    public ResponseEntity<DomainErrorResponse> manejarNombreMaquinaNoValidoException(NombreMaquinaNoValidoException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(MaquinaNoEncontradaException.class)
    public ResponseEntity<DomainErrorResponse> manejarMaquinaNoEncontrada(MaquinaNoEncontradaException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }
}