package com.fabrica.stock.producto.domain.vo;

import com.fabrica.stock.producto.domain.constraints.ProductoConstraints;
import com.fabrica.stock.producto.domain.exceptions.ProductoCodigoNoValidoException;

public record CodigoProducto(String valor) {
    public CodigoProducto {
        if(!ProductoConstraints.codigoProductoValido(valor))
            throw new ProductoCodigoNoValidoException("Código producto solo puede ser seis dígitos.");
    }

    public static CodigoProducto of(String valor) {
        return new CodigoProducto(valor);
    }
}