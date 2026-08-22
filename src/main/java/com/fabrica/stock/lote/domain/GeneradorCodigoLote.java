package com.fabrica.stock.lote.domain;

import com.fabrica.stock.maquina.domain.vo.NombreMaquina;

import java.time.LocalDateTime;

public final class GeneradorCodigoLote {
    private final LocalDateTime fechaCreacion;
    private final TurnoProduccion turnoProduccion;
    private final String codigo;

    public GeneradorCodigoLote(NombreMaquina nombreMaquina) {
        fechaCreacion = LocalDateTime.now();
        turnoProduccion = TurnoProduccion.obtener(fechaCreacion);
        codigo =  nombreMaquina.valor() + "-" + codigoSinMaquina();
    }

    public String getCodigo() { return codigo; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public TurnoProduccion getTurnoProduccion() { return turnoProduccion; }

    private String codigoSinMaquina() {
        String primerDigitoYear = String.valueOf(fechaCreacion.getYear()).substring(0,1);
        String ultimoDigitoYear = String.valueOf(fechaCreacion.getYear()).substring(3);
        String dia = String.format("%03d", fechaCreacion.getDayOfYear());
        return primerDigitoYear + dia + ultimoDigitoYear + "-" + turnoProduccion.getCodigo();
    }
}