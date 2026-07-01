package com.fabrica.stock.lote.domain.exceptions;

public class CodigoLoteDuplicadoException extends RuntimeException {
    public CodigoLoteDuplicadoException(String message) {
        super(message);
    }
}
