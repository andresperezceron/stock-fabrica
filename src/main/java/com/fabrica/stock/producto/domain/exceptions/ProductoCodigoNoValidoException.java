package com.fabrica.stock.producto.domain.exceptions;

public class ProductoCodigoNoValidoException extends RuntimeException {
    public ProductoCodigoNoValidoException(String message) {
        super(message);
    }
}
