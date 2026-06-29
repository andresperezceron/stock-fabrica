package com.fabrica.stock.producto.domain.exceptions;

public class CodigoProductoDuplicadoException extends RuntimeException {
    public CodigoProductoDuplicadoException(String message) {
        super(message);
    }
}
