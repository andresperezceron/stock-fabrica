package com.fabrica.stock.lote.domain.vo;

import com.fabrica.stock.lote.domain.TurnoProduccion;
import com.fabrica.stock.maquina.domain.vo.NombreMaquina;

import java.time.LocalDateTime;

public final class CodigoLote {
    private final NombreMaquina nombreMaquina;
    private final LocalDateTime fecha;

    public CodigoLote(NombreMaquina nombreMaquina, LocalDateTime fecha) {
        this.nombreMaquina = nombreMaquina;
        this.fecha = fecha;
    }
}