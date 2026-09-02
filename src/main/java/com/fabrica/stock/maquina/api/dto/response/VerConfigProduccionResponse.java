package com.fabrica.stock.maquina.api.dto.response;

import com.fabrica.stock.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.maquina.api.dto.PaletMateriaPrimaDto;
import com.fabrica.stock.maquina.domain.Maquina;

import java.util.List;

public record VerConfigProduccionResponse(
        String productoCodigo,
        String productoDesc,
        Integer cajasPorPalet,
        List<PaletMateriaPrimaDto> paletsMateriaPrima,
        String observaciones
) {
    public static VerConfigProduccionResponse of(Maquina maquina) {
        ConfigProduccion config = maquina.getConfigProduccion();
        if(config == null) return null;

        return new VerConfigProduccionResponse(
                config.getProducto().getCodigo().valor(),
                config.getProducto().getDescripcion(),
                config.getCajasPorPalet(),
                config.getMateriasPrimas()
                        .stream()
                        .map(PaletMateriaPrimaDto::of)
                        .toList(),
                config.getObservaciones()
        );
    }
}