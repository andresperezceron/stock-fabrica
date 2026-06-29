package com.fabrica.stock.ubicacion.api.validation;

import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;

public abstract class UbicacionRequestValidator<T> {

    protected final T request;
    protected final UbicacionRepository repository;

    protected UbicacionRequestValidator(
            T request,
            UbicacionRepository repository) {

        this.request = request;
        this.repository = repository;
    }

    public final void validate() {
        normalize();
        validateRules();
    }

    protected void normalize() {
        // por defecto no hace nada
    }

    protected abstract void validateRules();
}