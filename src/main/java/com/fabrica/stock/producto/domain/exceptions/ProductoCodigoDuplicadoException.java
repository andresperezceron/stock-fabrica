package com.fabrica.stock.producto.domain.exceptions;

public class ProductoCodigoDuplicadoException extends RuntimeException {
    public ProductoCodigoDuplicadoException(String message) {
        super(message);
    }
}
