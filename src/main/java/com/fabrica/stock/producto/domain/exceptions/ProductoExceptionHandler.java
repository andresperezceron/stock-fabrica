package com.fabrica.stock.producto.domain.exceptions;

import com.fabrica.stock.shared.response.DomainErrorResponse;
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

    @ExceptionHandler(CodigoProductoDuplicadoException.class)
    public ResponseEntity<DomainErrorResponse> manejarProductoDuplicado(CodigoProductoDuplicadoException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(CodigoProductoNoNumericoException.class)
    public ResponseEntity<DomainErrorResponse> manejarCodigoProductoNoNumerico(CodigoProductoNoNumericoException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(ProductoNoActivoException.class)
    public ResponseEntity<DomainErrorResponse> manejarProductoNoActivo(ProductoNoActivoException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }
}