package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.producto.application.ConfigProduccionService;
import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.shared.application.InicioProcesoProduccionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AsignarProductoActivoService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ObtenerProductoService obtenerProductoService;
    private final ConfigProduccionService configProduccionService;
    private final InicioProcesoProduccionService inicioProcesoProduccionService;

    public AsignarProductoActivoService(ObtenerMaquinaService obtenerMaquinaService,
                                        ObtenerProductoService obtenerProductoService,
                                        ConfigProduccionService configProduccionService,
                                        InicioProcesoProduccionService inicioProcesoProduccionService)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.obtenerProductoService = obtenerProductoService;
        this.configProduccionService = configProduccionService;
        this.inicioProcesoProduccionService = inicioProcesoProduccionService;
    }

    @Transactional
    public void asignar(Long id, Long productoActivoId) {
        Producto producto = obtenerProductoService.obtener(productoActivoId);
        Maquina maquina = obtenerMaquinaService.obtener(id);
        inicioProcesoProduccionService.validarAsignarProductoActivo(maquina, producto);
        maquina.asignarProductoActivo(producto);
        configProduccionService.crearSinoExiste(producto);
    }
}