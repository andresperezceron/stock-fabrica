package com.fabrica.stock.maquina.domain.exceptions;

public class MaquinaNoEncontradaException extends RuntimeException {
    public MaquinaNoEncontradaException(String message) {
        super(message);
    }
}
