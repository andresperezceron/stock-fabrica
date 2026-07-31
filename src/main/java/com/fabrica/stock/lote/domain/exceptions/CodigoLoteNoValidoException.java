package com.fabrica.stock.lote.domain.exceptions;

public class CodigoLoteNoValidoException extends RuntimeException {
    public CodigoLoteNoValidoException(String message) {
        super(message);
    }
}
