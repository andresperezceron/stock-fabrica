package com.fabrica.stock.palet.application;

import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.TipoPalet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import com.fabrica.stock.shared.application.InicioProcesoProduccionService;
import org.springframework.stereotype.Service;

@Service
public class CrearPaletCajaService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final PaletRepository paletRepository;
    private final InicioProcesoProduccionService inicioProcesoProduccionService;

    public CrearPaletCajaService(ObtenerMaquinaService obtenerMaquinaService,
                                 PaletRepository paletRepository,
                                 InicioProcesoProduccionService inicioProcesoProduccionService)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.paletRepository = paletRepository;
        this.inicioProcesoProduccionService = inicioProcesoProduccionService;
    }

    public void crear(Long maquinaId) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        inicioProcesoProduccionService.validarCreacionDePalet(maquina);
        paletRepository.save(new Palet(maquina, TipoPalet.CAJA));
    }
}