package com.fabrica.stock.configproduccion.domain.exceptions;

public class ConfigProduccionNoExisteException extends RuntimeException {
    public ConfigProduccionNoExisteException(String message) {
        super(message);
    }
}
