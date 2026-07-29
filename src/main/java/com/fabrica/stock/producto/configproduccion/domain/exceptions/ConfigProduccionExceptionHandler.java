package com.fabrica.stock.producto.configproduccion.domain.exceptions;

import com.fabrica.stock.shared.domain.DomainErrorResponse;
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

    @ExceptionHandler(MateriaPrimaNoValidaException.class)
    public ResponseEntity<DomainErrorResponse> manejarMateriaPrimaNoValida(MateriaPrimaNoValidaException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(ProductoActivoNoAsignadoException.class)
    public ResponseEntity<DomainErrorResponse>
    manejarProductoActivoNoAsignadoException(ProductoActivoNoAsignadoException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(ConfigProduccionNoValidaException.class)
    public ResponseEntity<DomainErrorResponse> manejarConfigProduccionNoValida(ConfigProduccionNoValidaException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(CajasPorPaletNoValidoException.class)
    public ResponseEntity<DomainErrorResponse> manejarCajasPorPalet(CajasPorPaletNoValidoException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }
}