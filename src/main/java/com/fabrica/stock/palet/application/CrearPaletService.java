package com.fabrica.stock.palet.application;

import com.fabrica.stock.palet.api.dto.PaletRequest;
import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import com.fabrica.stock.producto.application.ConfigProduccionService;
import org.springframework.stereotype.Service;

@Service
public class CrearPaletService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final PaletRepository paletRepository;
    private final ConfigProduccionService configProduccionService;

    public CrearPaletService(ObtenerMaquinaService obtenerMaquinaService,
                             PaletRepository paletRepository,
                             ConfigProduccionService configProduccionService) {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.paletRepository = paletRepository;
        this.configProduccionService = configProduccionService;
    }

    public void crear(PaletRequest request) {
        Maquina maquina = obtenerMaquinaService.obtener(request.maquinaId());
        configProduccionService.validarMaquinaParaProduccion(maquina);
        paletRepository.save(new Palet(maquina, request.tipoPalet()));
    }
}