package com.fabrica.stock.producto.configinyeccion.domain.exceptions;

import com.fabrica.stock.shared.response.DomainErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ConfigInyeccionExceptionHandler {
    @ExceptionHandler(NoAdmiteConfigInyeccionException.class)
    public ResponseEntity<DomainErrorResponse>
    manejarConfigProduccionNoEncontrada(NoAdmiteConfigInyeccionException ex) {
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
}