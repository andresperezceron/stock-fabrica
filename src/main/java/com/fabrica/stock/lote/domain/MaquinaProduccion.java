package com.fabrica.stock.lote.domain;

public enum MaquinaProduccion {
    A("A"),
    B("B"),
    C("C");

    private final String codigo;

    MaquinaProduccion(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}