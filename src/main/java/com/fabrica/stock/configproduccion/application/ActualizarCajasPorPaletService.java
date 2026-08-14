package com.fabrica.stock.configproduccion.application;

import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ActualizarCajasPorPaletService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;
    private final ComprobarActCajasPorPaletService comprobarActCajasPorPaletService;

    public ActualizarCajasPorPaletService(ObtenerMaquinaService obtenerMaquinaService,
                                          ObtenerConfigProduccionService obtenerConfigProduccionService,
                                          ComprobarActCajasPorPaletService comprobarActCajasPorPaletService)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
        this.comprobarActCajasPorPaletService = comprobarActCajasPorPaletService;
    }

    @Transactional
    public void actualizar(Long maquinaId, Integer cajasPorPalet) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        comprobarActCajasPorPaletService.comprobar(maquina, cajasPorPalet);

        obtenerConfigProduccionService
                    .porProducto(maquina.getProductoActivo())
                    .actualizarCajasPorPalet(cajasPorPalet);
    }
}