package com.fabrica.stock.maquina.api.dto.response;

import com.fabrica.stock.maquina.api.dto.PaletMateriaPrimaDto;
import com.fabrica.stock.maquina.domain.Maquina;

import java.util.List;

public record VerConfigProduccionResponse(
        String productoDesc,
        Integer cajasPorPalet,
        List<PaletMateriaPrimaDto> paletsMateriaPrima,
        String observaciones
) {
    public static VerConfigProduccionResponse of(Maquina maquina) {
        return new VerConfigProduccionResponse(
                maquina.getConfigProduccion().getProducto().getDescripcion(),
                maquina.getConfigProduccion().getCajasPorPalet(),
                maquina.getConfigProduccion().getMateriasPrimas().stream().map(PaletMateriaPrimaDto::of).toList(),
                maquina.getConfigProduccion().getObservaciones()
        );
    }
}