package com.fabrica.stock.ubicacion.domain;

public enum UbicacionSistema {
    PRODUCCION("PRODUCCION"),
    VALIDACION("VALIDACION"),
    PROVEEDOR("PROVEEDOR");

    private final String nombre;

    UbicacionSistema(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static boolean esUbicacionSistema(UbicacionSistema ubicacion) {
        return ubicacion == UbicacionSistema.PRODUCCION ||
                ubicacion == UbicacionSistema.PROVEEDOR ||
                ubicacion == UbicacionSistema.VALIDACION;
    }
}
