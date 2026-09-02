package com.fabrica.stock.maquina.api.dto.response;

import com.fabrica.stock.maquina.domain.EstadoMaquina;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.api.dto.response.PaletsConsumoInyeccionResponse;
import com.fabrica.stock.producto.api.dto.ProductosInyeccionResponse;

import java.time.LocalDateTime;

public record TerminalResponse(
        String nombreMaquina,
        EstadoMaquina estadoMaquina,
        LocalDateTime fechaCambioEstado,
        ProductosInyeccionResponse productos,
        PaletsConsumoInyeccionResponse palets,
        VerConfigProduccionResponse config
) {
    public static TerminalResponse of(
            Maquina maquina,
            ProductosInyeccionResponse productos,
            PaletsConsumoInyeccionResponse palets)
    {
        return new TerminalResponse(
                maquina.getNombre().valor(),
                maquina.getEstado(),
                maquina.getFechaCambioEstado(),
                productos,
                palets,
                VerConfigProduccionResponse.of(maquina)
        );
    }
}