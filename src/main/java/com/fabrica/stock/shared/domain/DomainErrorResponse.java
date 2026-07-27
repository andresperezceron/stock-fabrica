package com.fabrica.stock.shared.domain;

public record DomainErrorResponse(String mensaje) {
    public static DomainErrorResponse of(String message) {
        return new DomainErrorResponse(message);
    }
}