package com.fabrica.stock.configproduccion.application;

import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.application.ComprobarPaletAbiertoService;
import com.fabrica.stock.palet.domain.exceptions.EstadoPaletInvalidoException;
import com.fabrica.stock.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class QuitarMateriaPrimaService {
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ObtenerProductoService obtenerProductoService;
    private final ComprobarPaletAbiertoService comprobarPaletAbiertoService;

    public QuitarMateriaPrimaService(ObtenerConfigProduccionService obtenerConfigProduccionService,
                                     ObtenerMaquinaService obtenerMaquinaService,
                                     ObtenerProductoService obtenerProductoService,
                                     ComprobarPaletAbiertoService comprobarPaletAbiertoService)
    {
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.obtenerProductoService = obtenerProductoService;
        this.comprobarPaletAbiertoService = comprobarPaletAbiertoService;
    }

    @Transactional
    public void quitar(Long maquinaId, Long materiaPrimaId) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        Producto materiaPrima = obtenerProductoService.obtener(materiaPrimaId);
        ConfigProduccion config = obtenerConfigProduccionService.porProducto(maquina.getProductoActivo());

        if(maquina.estaProduccion() && comprobarPaletAbiertoService.comprobar(maquina))
            throw new EstadoPaletInvalidoException( "No se puede modificar la configuración mientras " +
                    "exista un contenido abierto.");
        config.quitarMateriaPrima(materiaPrima);
    }
}