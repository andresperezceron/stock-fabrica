package com.fabrica.stock.configproduccion.application;

import com.fabrica.stock.maquina.application.ObtenerMaquinaService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.application.ComprobarPaletAbiertoService;
import com.fabrica.stock.palet.application.ComprobarTipoPaletGranelService;
import com.fabrica.stock.palet.domain.exceptions.EstadoPaletInvalidoException;
import com.fabrica.stock.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.configproduccion.domain.exceptions.MateriaPrimaDupicadaException;
import com.fabrica.stock.configproduccion.domain.exceptions.MateriaPrimaNoValidaException;
import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AgregarMateriaPrimaService {
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ObtenerProductoService obtenerProductoService;
    private final ComprobarPaletAbiertoService comprobarPaletAbiertoService;
    private final ComprobarTipoPaletGranelService comprobarTipoPaletGranelService;

    public AgregarMateriaPrimaService(ObtenerConfigProduccionService obtenerConfigProduccionService,
                                      ObtenerMaquinaService obtenerMaquinaService,
                                      ObtenerProductoService obtenerProductoService,
                                      ComprobarPaletAbiertoService comprobarPaletAbiertoService,
                                      ComprobarTipoPaletGranelService comprobarTipoPaletGranelService)
    {
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.obtenerProductoService = obtenerProductoService;
        this.comprobarPaletAbiertoService = comprobarPaletAbiertoService;
        this.comprobarTipoPaletGranelService = comprobarTipoPaletGranelService;
    }

    @Transactional
    public void agregar(Long maquinaId, Long materiaPrimaId) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        Producto materiaPrima = obtenerProductoService.obtener(materiaPrimaId);
        ConfigProduccion config = obtenerConfigProduccionService.porProducto(maquina.getProductoActivo());

        if(!materiaPrima.esMateriaPrima()) throw new MateriaPrimaNoValidaException("El producto no es materia prima.");
        else if(config.contieneMateriaPrima(materiaPrima))
            throw new MateriaPrimaDupicadaException("La materia prima ya está agregada.");
        else if(maquina.estaProduccion() && comprobarTipoPaletGranelService.comprobar(maquina))
            throw new EstadoPaletInvalidoException("Tengo que crear esta excepcion.");
        else if(maquina.estaProduccion() && comprobarPaletAbiertoService.comprobar(maquina))
            throw new EstadoPaletInvalidoException("El palet no esta cerrado.");
        else config.agregarMateriaPrima(materiaPrima);
    }
}