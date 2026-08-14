package com.fabrica.stock.configproduccion.application;

import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.movimiento.application.CrearMovimientoService;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.configproduccion.domain.ConfigProduccion;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ConsumirMateriaPrimaService {
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ObtenerPaletService obtenerPaletService;
    private final CrearMovimientoService crearMovimientoService;
    private final ComprobarConsumirMatPriService comprobarConsumirMatPriService;

    public ConsumirMateriaPrimaService(ObtenerConfigProduccionService obtenerConfigProduccionService,
                                       ObtenerMaquinaService obtenerMaquinaService,
                                       ObtenerPaletService obtenerPaletService,
                                       CrearMovimientoService crearMovimientoService,
                                       ComprobarConsumirMatPriService comprobarConsumirMatPriService)
    {
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.obtenerPaletService = obtenerPaletService;
        this.crearMovimientoService = crearMovimientoService;
        this.comprobarConsumirMatPriService = comprobarConsumirMatPriService;
    }

    @Transactional
    public void consumir(Long maquinaId, Long paletId) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        Palet palet = obtenerPaletService.obtener(paletId);
        ConfigProduccion config = obtenerConfigProduccionService.porProducto(maquina.getProductoActivo());
        comprobarConsumirMatPriService.comprobar(maquina, palet, config);

        crearMovimientoService.consumicion(palet);
        palet.consumido();
        config.consumirMateriaPrima(palet);

        if(config.esValida()) maquina.configurada();
        else maquina.enConfiguracion();
    }
}