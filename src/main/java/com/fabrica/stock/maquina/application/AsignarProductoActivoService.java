package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.domain.Maquina;
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

    public AsignarProductoActivoService(ObtenerMaquinaService obtenerMaquinaService,
                                        ObtenerProductoService obtenerProductoService,
                                        ConfigProduccionService configProduccionService)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.obtenerProductoService = obtenerProductoService;
        this.configProduccionService = configProduccionService;
    }

    @Transactional
    public void asignar(Long id, Long productoActivoId) {
        Producto producto = obtenerProductoService.obtener(productoActivoId);
        Maquina maquina = obtenerMaquinaService.obtener(id);

        if(!maquina.getTipo().puedeFabricar(producto)) {
            throw new ProductoActivoNoValidoException("Producto incompatible con la maquina: " + maquina.getNombre());
        }

        //aqui estaría la validacion del palet si no hay palet. lanzo excepcion. fin.

        maquina.asignarProductoActivo(producto);
        configProduccionService.crearSinoExiste(producto);
    }
}