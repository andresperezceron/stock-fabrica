package com.fabrica.stock.producto.domain.constraints;

public final class ProductoConstraints {
    public static final int CODIGO = 6;

    private ProductoConstraints() {}
    public static boolean codigoProductoValido(String codigo) {
        return codigo.matches("\\d{"+CODIGO+"}"); }
}