package com.fabrica.stock.shared.domain;

public class CodigoProductoMaxLengthException extends RuntimeException {
    public CodigoProductoMaxLengthException(String message) {
        super(message);
    }
}
