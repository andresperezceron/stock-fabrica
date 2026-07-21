package com.fabrica.stock.shared.response;

public class DomainErrorResponse {
    private final String message;

    private DomainErrorResponse(String message) {
        this.message = message;
    }

    public static DomainErrorResponse of(String message) {
        return new DomainErrorResponse(message);
    }

    public String getMessage() {
        return message;
    }
}
