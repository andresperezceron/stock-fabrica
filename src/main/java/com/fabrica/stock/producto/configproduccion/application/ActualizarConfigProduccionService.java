package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.lote.application.ObtenerLoteService;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.producto.configproduccion.api.dto.ActualizarConfigProduccionRequest;
import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.domain.validation.ActualizarConfigProduccionValidator;
import com.fabrica.stock.producto.configproduccion.infrastructure.ConfigProduccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActualizarConfigProduccionService {
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;
    private final ObtenerLoteService obtenerLoteService;
    private final ConfigProduccionRepository configProduccionRepository;

    public ActualizarConfigProduccionService(ObtenerConfigProduccionService obtenerConfigProduccionService,
                                             ObtenerLoteService obtenerLoteService,
                                             ConfigProduccionRepository configProduccionRepository) {
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
        this.obtenerLoteService = obtenerLoteService;
        this.configProduccionRepository = configProduccionRepository;
    }

    public void actualizar(ActualizarConfigProduccionRequest request) {
        new ActualizarConfigProduccionValidator(request, configProduccionRepository).validate();
        ConfigProduccion config = obtenerConfigProduccionService.obtener(request.id);
        List<Lote> lotesMateriaPrima = request.lotesMateriaPrima.stream()
                .map(obtenerLoteService::obtener)
                .toList();
        config.actualizar(request.observaciones,  lotesMateriaPrima);
    }
}