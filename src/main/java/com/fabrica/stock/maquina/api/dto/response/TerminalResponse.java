package com.fabrica.stock.maquina.api.dto.response;

import com.fabrica.stock.maquina.domain.EstadoMaquina;
import com.fabrica.stock.maquina.domain.Maquina;

public record TerminalResponse(
        String nombreMaquina,
        EstadoMaquina estadoMaquina
) {
    public static TerminalResponse of(Maquina maquina) {
        return new TerminalResponse(
                maquina.getNombre().valor(),
                maquina.getEstado()
        );
    }
}