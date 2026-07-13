package com.fabrica.stock.ubicacion.domain;

public enum UbicacionSistema {
    PRODUCCION,
    VALIDACION,
    PROVEEDOR,
    EXPEDICION;

    public static boolean esUbicacionSistema(String nombreUbicacion) {
        return nombreUbicacion.toUpperCase().equals(PRODUCCION.name()) ||
                nombreUbicacion.toUpperCase().equals(VALIDACION.name()) ||
                nombreUbicacion.toUpperCase().equals(PROVEEDOR.name()) ||
                nombreUbicacion.toUpperCase().equals(EXPEDICION.name());
    }
}