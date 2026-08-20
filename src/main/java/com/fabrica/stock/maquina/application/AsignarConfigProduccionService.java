package com.fabrica.stock.maquina.application;

import com.fabrica.stock.configproduccion.application.CrearConfigProduccionService;
import com.fabrica.stock.maquina.api.dto.request.IdProductoRequest;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AsignarConfigProduccionService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ObtenerProductoService obtenerProductoService;
    private final CrearConfigProduccionService crearConfigProduccionService;

    public AsignarConfigProduccionService(ObtenerMaquinaService obtenerMaquinaService,
                                          ObtenerProductoService obtenerProductoService,
                                          CrearConfigProduccionService crearConfigProduccionService)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.obtenerProductoService = obtenerProductoService;
        this.crearConfigProduccionService = crearConfigProduccionService;
    }

    @Transactional
    public void asignar(Long maquinaId, IdProductoRequest request) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        Producto producto = obtenerProductoService.obtener(request.productoId());

        if(maquina.estaMantenimiento() || maquina.estaCambioMolde() || maquina.estaFueraServicio())
            throw new EstadoNoValidoException("Servicio no disponible.");

        if(maquina.estaEnProduccion())
            throw new EstadoNoValidoException("Servicio no disponible.");

        if(!maquina.puedeFabricar(producto))
            throw new RecursoNoValidoException("Máquina no puede fabricar: " + producto.getDescripcion());

        maquina.asignarConfigProduccion(crearConfigProduccionService.porProductoSiNoExiste(producto));
    }
}