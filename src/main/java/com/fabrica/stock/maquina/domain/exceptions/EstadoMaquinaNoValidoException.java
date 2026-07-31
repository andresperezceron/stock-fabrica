package com.fabrica.stock.maquina.domain.exceptions;

public class EstadoMaquinaNoValidoException extends RuntimeException {
    public EstadoMaquinaNoValidoException(String message) {
        super(message);
    }
}
