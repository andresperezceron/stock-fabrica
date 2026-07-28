package com.fabrica.stock.estacion.domain.exceptions;

public class EstacionOcupadaException extends RuntimeException {
    public EstacionOcupadaException(String message) {
        super(message);
    }
}
