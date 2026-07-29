package com.fabrica.stock.palet.application;

import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.TipoPalet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import com.fabrica.stock.producto.application.ConfigProduccionService;
import org.springframework.stereotype.Service;

@Service
public class CrearPaletCajaService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final PaletRepository paletRepository;
    private final ConfigProduccionService configProduccionService;

    public CrearPaletCajaService(ObtenerMaquinaService obtenerMaquinaService,
                                 PaletRepository paletRepository,
                                 ConfigProduccionService configProduccionService) {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.paletRepository = paletRepository;
        this.configProduccionService = configProduccionService;
    }

    public void crear(Long maquinaId) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        configProduccionService.validarProcesoDeProduccion(maquina);
        paletRepository.save(new Palet(maquina, TipoPalet.CAJA));
    }
}