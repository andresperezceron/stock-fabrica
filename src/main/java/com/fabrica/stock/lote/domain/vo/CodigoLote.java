package com.fabrica.stock.lote.domain.vo;


public record CodigoLote(String valor) {
    public static CodigoLote of(String valor) {
        return new CodigoLote(valor);
    }
}
