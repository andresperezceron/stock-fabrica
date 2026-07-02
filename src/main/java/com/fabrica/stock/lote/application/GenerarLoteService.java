package com.fabrica.stock.lote.application;

import com.fabrica.stock.lote.domain.MaquinaProduccion;
import com.fabrica.stock.lote.domain.TurnoProduccion;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GenerarLoteService {
    public String generar(MaquinaProduccion maquina, LocalDateTime fecha) {
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