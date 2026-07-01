package com.fabrica.stock.lote.application;

import com.fabrica.stock.lote.domain.MaquinaProduccion;
import com.fabrica.stock.lote.domain.TurnoProduccion;

import java.time.LocalDateTime;

public class GenerarLoteService {
    public String generar(MaquinaProduccion maquina) {
        LocalDateTime fecha = LocalDateTime.now();
        String primerDigitoYear = String.valueOf(fecha.getYear()).substring(0, 1);
        String ultimoDigitoYear = String.valueOf(fecha.getYear()).substring(3);
        TurnoProduccion turno = TurnoProduccion.obtener(fecha);

        return maquina.getCodigo() + "-" +
                primerDigitoYear +
                fecha.getDayOfYear() +
                ultimoDigitoYear + "-" +
                turno.getCodigo();
    }
}