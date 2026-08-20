package com.fabrica.stock.maquina.api.dto.response;

import com.fabrica.stock.maquina.domain.EstadoMaquina;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.maquina.domain.TipoMaquina;

import java.time.LocalDateTime;

public record MaquinaResponse(
        Long id,
        TipoMaquina tipo,
        String nombre,
        String descripcion,
        Long configId,
        Long paletId,
        EstadoMaquina estado,
        LocalDateTime fechaCambioEstado
) {
    public static MaquinaResponse of(Maquina maquina) {
        return new MaquinaResponse(
                maquina.getId(),
                maquina.getTipo(),
                maquina.getNombre().valor(),
                maquina.getDescripcion(),
                maquina.getConfigProduccion() != null ? maquina.getConfigProduccion().getId() : null,
                maquina.getPalet() != null ? maquina.getPalet().getId() : null,
                maquina.getEstado(),
                maquina.getFechaCambioEstado()
        );
    }
}