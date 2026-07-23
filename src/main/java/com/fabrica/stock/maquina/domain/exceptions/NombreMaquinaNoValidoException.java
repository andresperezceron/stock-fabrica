package com.fabrica.stock.maquina.domain.exceptions;

public class NombreMaquinaNoValidoException extends RuntimeException {
    public NombreMaquinaNoValidoException(String message) {
        super(message);
    }
}
