package com.fabrica.stock.shared.response;

public class DomainErrorResponse {
    private final String message;

    public DomainErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
