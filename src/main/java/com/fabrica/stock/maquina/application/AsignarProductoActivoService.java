package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.maquina.domain.exceptions.EstadoMaquinaNoValidoException;
import com.fabrica.stock.maquina.domain.exceptions.ProductoActivoNoValidoException;
import com.fabrica.stock.producto.application.ConfigProduccionService;
import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AsignarProductoActivoService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ObtenerProductoService obtenerProductoService;
    private final ConfigProduccionService configProduccionService;
    private final CambiarEstadoMaquinaService cambiarEstadoMaquinaService;

    public AsignarProductoActivoService(ObtenerMaquinaService obtenerMaquinaService,
                                        ObtenerProductoService obtenerProductoService,
                                        ConfigProduccionService configProduccionService,
                                        CambiarEstadoMaquinaService cambiarEstadoMaquinaService)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.obtenerProductoService = obtenerProductoService;
        this.configProduccionService = configProduccionService;
        this.cambiarEstadoMaquinaService = cambiarEstadoMaquinaService;
    }

    @Transactional
    public void asignar(Long id, Long productoActivoId) {
        Maquina maquina = obtenerMaquinaService.obtener(id);
        if(!maquina.estaAptaProduccion()) {
            throw new EstadoMaquinaNoValidoException("No se puede asignar producto activo con máquina en estado: " +
                    maquina.getEstado());
        }

        Producto producto = obtenerProductoService.obtener(productoActivoId);
        if(!maquina.getTipo().puedeFabricar(producto)) {
            throw new ProductoActivoNoValidoException("Producto incompatible con la máquina.");
        }

        maquina.asignarProductoActivo(producto);
        configProduccionService.crearSinoExiste(producto);

        if(configProduccionService.esValida(producto))
            maquina.configurada();
    }
}