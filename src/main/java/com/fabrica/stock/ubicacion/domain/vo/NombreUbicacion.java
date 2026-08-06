package com.fabrica.stock.ubicacion.domain.vo;

public record NombreUbicacion(String valor) {
    public NombreUbicacion {
        valor = valor.trim().toUpperCase();
    }

    public static NombreUbicacion of(String valor) {
        return new NombreUbicacion(valor);
    }
}
