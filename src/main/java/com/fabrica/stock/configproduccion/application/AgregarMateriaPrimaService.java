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
public class AgregarMateriaPrimaService {
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ObtenerPaletService obtenerPaletService;
    private final ComprobarAgregarMatPriService comprobarAgregarMatPriService;
    private final CrearMovimientoService crearMovimientoService;

    public AgregarMateriaPrimaService(ObtenerConfigProduccionService obtenerConfigProduccionService,
                                      ObtenerMaquinaService obtenerMaquinaService,
                                      ObtenerPaletService obtenerPaletService,
                                      ComprobarAgregarMatPriService comprobarAgregarMatPriService,
                                      CrearMovimientoService crearMovimientoService)
    {
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.obtenerPaletService = obtenerPaletService;
        this.comprobarAgregarMatPriService = comprobarAgregarMatPriService;
        this.crearMovimientoService = crearMovimientoService;
    }

    @Transactional
    public void agregar(Long maquinaId, Long paletId) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        Palet palet = obtenerPaletService.obtener(paletId);
        ConfigProduccion config = obtenerConfigProduccionService.porProducto(maquina.getProductoActivo());
        comprobarAgregarMatPriService.comprobar(maquina, palet, config);

        crearMovimientoService.paraConsumo(palet);
        palet.enConsumo();
        config.agregarMateriaPrima(palet);

        if(config.esValida()) maquina.configurada();
        else maquina.enConfiguracion();
    }
}