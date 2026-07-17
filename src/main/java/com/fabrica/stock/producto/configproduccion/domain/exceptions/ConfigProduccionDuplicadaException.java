package com.fabrica.stock.producto.configproduccion.domain.exceptions;

public class ConfigProduccionDuplicadaException extends RuntimeException {
    public ConfigProduccionDuplicadaException(String message) {
        super(message);
    }
}
