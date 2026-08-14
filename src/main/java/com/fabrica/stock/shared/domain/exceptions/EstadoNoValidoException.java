package com.fabrica.stock.shared.domain.exceptions;

public class EstadoNoValidoException extends RuntimeException {
    public EstadoNoValidoException(String message) {
        super(message);
    }
}
