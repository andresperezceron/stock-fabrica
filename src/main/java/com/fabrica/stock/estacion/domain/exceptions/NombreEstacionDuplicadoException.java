package com.fabrica.stock.estacion.domain.exceptions;

public class NombreEstacionDuplicadoException extends RuntimeException {
    public NombreEstacionDuplicadoException(String message) {
        super(message);
    }
}
