package com.fabrica.stock.producto.domain.vo;

import com.fabrica.stock.producto.domain.exceptions.ProductoCodigoNoValidoException;

import java.util.regex.Pattern;

public record CodigoProducto(String valor) {
    private static final Pattern PATRON = Pattern.compile("\\d{6}");

    public CodigoProducto {
        if(!PATRON.matcher(valor).matches())
            throw new ProductoCodigoNoValidoException("Código producto solo puede ser seis dígitos.");
    }

    public static CodigoProducto of(String valor) {
        return new CodigoProducto(valor);
    }
}