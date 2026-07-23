package com.fabrica.stock.producto.configproduccion.domain.exceptions;

import com.fabrica.stock.shared.response.DomainErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ConfigProduccionExceptionHandler {
    @ExceptionHandler(ConfigProduccionNoExisteException.class)
    public ResponseEntity<DomainErrorResponse>
    manejarConfigProduccionNoExiste(ConfigProduccionNoExisteException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(MateriaPrimaDupicadaException.class)
    public ResponseEntity<DomainErrorResponse> manejarMateriaPrimaDuplicada(MateriaPrimaDupicadaException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(ExisteConfigProduccionException.class)
    public ResponseEntity<DomainErrorResponse> manejarConfigProduccionSiExiste(ExisteConfigProduccionException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }
}