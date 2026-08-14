package com.fabrica.stock.palet.application;

import com.fabrica.stock.configproduccion.application.ObtenerConfigProduccionService;
import com.fabrica.stock.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.api.dto.MaquinaIdPaletIdRequest;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.shared.domain.exceptions.ConflictException;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AbrirPaletService {
    private final ObtenerPaletService obtenerPaletService;
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;
    private final ComprobarAbrirPaletService comprobarAbrirPaletService;

    public AbrirPaletService(ObtenerPaletService obtenerPaletService,
                             ObtenerMaquinaService obtenerMaquinaService,
                             ObtenerConfigProduccionService obtenerConfigProduccionService,
                             ComprobarAbrirPaletService comprobarAbrirPaletService)
    {
        this.obtenerPaletService = obtenerPaletService;
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
        this.comprobarAbrirPaletService = comprobarAbrirPaletService;
    }

    @Transactional
    public void abrir(MaquinaIdPaletIdRequest request) {
        Maquina maquina = obtenerMaquinaService.obtener(request.maquinaId());
        Palet palet = obtenerPaletService.obtener(request.paletId());
        ConfigProduccion config = obtenerConfigProduccionService.porProducto(maquina.getProductoActivo());

        comprobarAbrirPaletService.comprobar(maquina, palet, config);
        palet.abrir();
    }
}