package com.fabrica.stock.maquina.application;

import com.fabrica.stock.configproduccion.application.CrearConfigProduccionService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AsignarProductoActivoService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ObtenerProductoService obtenerProductoService;
    private final ComprobarAsignarProActService comprobarAsignarProActService;
    private final CrearConfigProduccionService crearConfigProduccionService;

    public AsignarProductoActivoService(ObtenerMaquinaService obtenerMaquinaService,
                                        ObtenerProductoService obtenerProductoService,
                                        ComprobarAsignarProActService comprobarAsignarProActService,
                                        CrearConfigProduccionService crearConfigProduccionService)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.obtenerProductoService = obtenerProductoService;
        this.comprobarAsignarProActService = comprobarAsignarProActService;
        this.crearConfigProduccionService = crearConfigProduccionService;
    }

    @Transactional
    public void asignar(Long id, Long productoActivoId) {
        Maquina maquina = obtenerMaquinaService.obtener(id);
        Producto producto = obtenerProductoService.obtener(productoActivoId);

        comprobarAsignarProActService.comprobar(maquina, producto);

        maquina.asignarProductoActivo(producto);
        if(crearConfigProduccionService.porProductoSiNoExiste(producto).esValida())
            maquina.configurada();
        else maquina.enConfiguracion();
    }
}