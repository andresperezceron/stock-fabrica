package com.fabrica.stock.maquina.domain.exceptions;

public class ProductoActivoNoValidoException extends RuntimeException {
    public ProductoActivoNoValidoException(String message) {
        super(message);
    }
}
