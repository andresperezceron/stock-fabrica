package com.fabrica.stock.producto.configproduccion.domain.exceptions;

public class ConfigProduccionNoExisteException extends RuntimeException {
    public ConfigProduccionNoExisteException(String message) {
        super(message);
    }
}
