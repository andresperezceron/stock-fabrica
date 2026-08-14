package com.fabrica.stock.producto.domain.vo;

import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;

import java.util.regex.Pattern;

public record CodigoProducto(String valor) {
    private static final Pattern PATRON = Pattern.compile("\\d{6}");

    public CodigoProducto {
        if(!PATRON.matcher(valor).matches())
            throw new RecursoNoValidoException("Código producto solo puede ser seis dígitos.");
    }

    public static CodigoProducto of(String valor) {
        return new CodigoProducto(valor);
    }
}