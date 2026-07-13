package com.fabrica.stock.producto.domain.exceptions;

import com.fabrica.stock.shared.response.DomainErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductoExceptionHandler {

    @ExceptionHandler(ProductoNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public DomainErrorResponse manejarProductoNoEncontrado(ProductoNoEncontradoException ex) {
        return new DomainErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(CodigoProductoDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public DomainErrorResponse manejarProductoDuplicado(CodigoProductoDuplicadoException ex) {
        return new DomainErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(CodigoProductoNoNumericoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public DomainErrorResponse manejarCodigoProductoNoNumerico(CodigoProductoNoNumericoException ex) {
        return new DomainErrorResponse(ex.getMessage());
    }
}