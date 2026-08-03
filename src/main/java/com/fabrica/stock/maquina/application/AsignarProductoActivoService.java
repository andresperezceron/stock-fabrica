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
        Maquina maquina = obtenerMaquinaService.obtener(id);

        if(maquina.estaProduccion()) throw new EstadoMaquinaNoValidoException("Máquina está en producción.");
        else if(maquina.estaMantenimiento() || maquina.estaCambioMolde() || maquina.estaFueraServicio())
            throw new EstadoMaquinaNoValidoException("No se puede asignar producto activo. " +
                    "Estado máquina: " + maquina.getEstado());

        Producto producto = obtenerProductoService.obtener(productoActivoId);
        if(!maquina.getTipo().puedeFabricar(producto))
            throw new ProductoActivoNoValidoException("La máquina no puede fabricar: " + producto.getDescripcion());

        maquina.asignarProductoActivo(producto);
        if(configProduccionService.crearSinoExiste(producto).esValida())
            maquina.configurada();
        else maquina.enConfiguracion();
    }
}