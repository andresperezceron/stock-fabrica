package com.fabrica.stock.maquina.api.dto.response;

import com.fabrica.stock.maquina.domain.EstadoMaquina;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.producto.api.dto.ProductosInyeccionResponse;

import java.time.LocalDateTime;

public record TerminalResponse(
        String nombreMaquina,
        EstadoMaquina estadoMaquina,
        LocalDateTime fechaCambioEstado,
        ProductosInyeccionResponse productos
) {
    public static TerminalResponse of(Maquina maquina, ProductosInyeccionResponse productos) {
        return new TerminalResponse(
                maquina.getNombre().valor(),
                maquina.getEstado(),
                maquina.getFechaCambioEstado(),
                productos
        );
    }
}