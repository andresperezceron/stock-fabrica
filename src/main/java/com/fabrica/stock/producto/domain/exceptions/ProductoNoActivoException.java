package com.fabrica.stock.producto.domain.exceptions;

public class ProductoNoActivoException extends RuntimeException {
    public ProductoNoActivoException(String message) {
        super(message);
    }
}
