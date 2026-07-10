package com.fabrica.stock.lote.application;

import com.fabrica.stock.lote.domain.MaquinaProduccion;
import com.fabrica.stock.lote.domain.TurnoProduccion;

import java.time.LocalDateTime;

public class LoteGenerado {
    private final MaquinaProduccion maquina;
    private final TurnoProduccion turno;
    private final String codigoLote;

    public LoteGenerado(MaquinaProduccion maquina, LocalDateTime fecha) {
        String primerDigitoYear = String.valueOf(fecha.getYear()).substring(0, 1);
        String ultimoDigitoYear = String.valueOf(fecha.getYear()).substring(3);
        String dia = String.format("%03d", fecha.getDayOfYear());
        this.maquina = maquina;
        turno = TurnoProduccion.obtener(fecha);
        codigoLote =
                maquina.getCodigo() + "-" +
                primerDigitoYear +
                dia +
                ultimoDigitoYear + "-" +
                turno.getCodigo();
    }

    public String getCodigoLote() { return codigoLote; }

    public MaquinaProduccion getMaquina() { return maquina; }

    public TurnoProduccion getTurno() { return turno; }
}