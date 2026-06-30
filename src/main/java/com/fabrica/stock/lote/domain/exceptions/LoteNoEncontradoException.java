package com.fabrica.stock.lote.domain.exceptions;

public class LoteNoEncontradoException extends RuntimeException {
    public LoteNoEncontradoException(String message) {
        super(message);
    }
}
