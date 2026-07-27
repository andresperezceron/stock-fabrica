package com.fabrica.stock.producto.domain.exceptions;

import com.fabrica.stock.shared.domain.DomainErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductoExceptionHandler {

    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<DomainErrorResponse> manejarProductoNoEncontrado(ProductoNoEncontradoException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(ProductoCodigoDuplicadoException.class)
    public ResponseEntity<DomainErrorResponse> manejarProductoCodigoDuplicado(ProductoCodigoDuplicadoException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(ProductoCodigoNoValidoException.class)
    public ResponseEntity<DomainErrorResponse> manejarCodigoProductoNoValido(ProductoCodigoNoValidoException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(ProductoActivoEnMaquinaException.class)
    public ResponseEntity<DomainErrorResponse> manejarProductoActivoEnMaquina(ProductoActivoEnMaquinaException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }
}