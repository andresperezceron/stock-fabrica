package com.fabrica.stock.lote.domain;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

public enum TurnoProduccion {

    MANANA("M"),
    TARDE("T"),
    NOCHE("N");

    private final String codigo;

    TurnoProduccion(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public static TurnoProduccion obtener(LocalDateTime fecha) {
        int hora = fecha.getHour();

        if(hora >= 6 && hora < 14) return MANANA;
        else if(hora >= 14 && hora < 22) return TARDE;
        else return NOCHE;
    }

    public static String regex() {
        return Arrays.stream(values())
                .map(TurnoProduccion::getCodigo)
                .collect(Collectors.joining("|"));
    }
}