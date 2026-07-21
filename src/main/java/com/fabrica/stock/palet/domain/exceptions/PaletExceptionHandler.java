package com.fabrica.stock.palet.domain.exceptions;

import com.fabrica.stock.shared.response.DomainErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PaletExceptionHandler {
    @ExceptionHandler(PaletNoEncontradoException.class)
    public ResponseEntity<DomainErrorResponse> manejarPaletNoEncontrado(PaletNoEncontradoException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(EstadoPaletInvalidoException.class)
    public ResponseEntity<DomainErrorResponse> manejarEstadoPaletInvalido(EstadoPaletInvalidoException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }
}
