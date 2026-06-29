package com.fabrica.stock.shared.domain;

public class CodigoProductoIsBlanckException extends RuntimeException {
    public CodigoProductoIsBlanckException(String message) {
        super(message);
    }
}
