package com.fabrica.stock.maquina.api.dto.response;

import com.fabrica.stock.maquina.domain.EstadoMaquina;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.producto.api.dto.DtoProductoInyeccion;
import com.fabrica.stock.producto.api.dto.ProductosInyeccionResponse;

import java.util.List;

public record TerminalResponse(
        String nombreMaquina,
        EstadoMaquina estadoMaquina,
        ProductosInyeccionResponse productos
) {
    public static TerminalResponse of(Maquina maquina, ProductosInyeccionResponse productos) {
        return new TerminalResponse(
                maquina.getNombre().valor(),
                maquina.getEstado(),
                productos
        );
    }
}