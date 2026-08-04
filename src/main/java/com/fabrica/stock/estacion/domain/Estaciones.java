package com.fabrica.stock.estacion.domain;

import java.util.List;

public final class Estaciones {
    private Estaciones() {}

    public static final String PRODUCCION = "PRODUCCION";
    public static final String VALIDACION = "VALIDACION";
    public static final String PROVEEDOR = "PROVEEDOR";
    public static final String EXPEDICION = "EXPEDICION";
    public static final String CORRECCION = "CORRECCION";

    public static List<String> todas() {
        return List.of(
                PRODUCCION,
                VALIDACION,
                PROVEEDOR,
                EXPEDICION,
                CORRECCION
        );
    }
}