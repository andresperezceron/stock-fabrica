package com.fabrica.stock.shared.domain.validation;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class RequestValidator<Q, E, R extends JpaRepository<E, Long>> {
    protected final Q request;
    protected final R repository;

    public RequestValidator(Q request, R repository) {
        this.request = request;
        this.repository = repository;
    }

    public final void validate() {
        normalize();
        validateBusinessRules();
    }

    protected void normalize() {
        // por defecto no hace nada
    }

    protected abstract void validateBusinessRules();
}