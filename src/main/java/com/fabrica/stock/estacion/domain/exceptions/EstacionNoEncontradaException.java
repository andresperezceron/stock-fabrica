package com.fabrica.stock.estacion.domain.exceptions;

public class EstacionNoEncontradaException extends RuntimeException {
    public EstacionNoEncontradaException(String message) {
        super(message);
    }
}
