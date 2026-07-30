package com.fabrica.stock.palet.application;

import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.TipoPalet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import com.fabrica.stock.producto.application.ConfigProduccionService;
import com.fabrica.stock.shared.application.InicioProcesoProduccionService;
import org.springframework.stereotype.Service;

@Service
public class CrearPaletGranelService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final PaletRepository paletRepository;
    private final InicioProcesoProduccionService inicioProcesoProduccionService;

    public CrearPaletGranelService(ObtenerMaquinaService obtenerMaquinaService,
                                   PaletRepository paletRepository,
                                   ConfigProduccionService configProduccionService,
                                   InicioProcesoProduccionService inicioProcesoProduccionService)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.paletRepository = paletRepository;
        this.inicioProcesoProduccionService = inicioProcesoProduccionService;
    }

    public void crear(Long maquinaId) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        inicioProcesoProduccionService.validarCreacionDePalet(maquina);
        paletRepository.save(new Palet(maquina, TipoPalet.GRANEL));
    }
}