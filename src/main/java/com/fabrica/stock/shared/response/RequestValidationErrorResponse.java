package com.fabrica.stock.shared.response;

import java.util.Map;

public class RequestValidationErrorResponse {
    private final Map<String, String> errors;

    public RequestValidationErrorResponse(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() { return errors; }
}
