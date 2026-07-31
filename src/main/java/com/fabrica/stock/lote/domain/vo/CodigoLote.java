package com.fabrica.stock.lote.domain.vo;

import com.fabrica.stock.lote.domain.constraints.LoteConstraints;
import com.fabrica.stock.lote.domain.exceptions.CodigoLoteNoValidoException;

public record CodigoLote(String valor) {
    public CodigoLote {
        if(!LoteConstraints.esCodigoLoteValido(valor))
            throw new CodigoLoteNoValidoException("Código de lote no válido: " + valor);
    }

    public static CodigoLote of(String valor) {
        return new CodigoLote(valor);
    }
}