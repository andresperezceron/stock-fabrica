package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.estacion.application.EstacionOcupadaService;
import com.fabrica.stock.estacion.domain.exceptions.EstacionOcupadaException;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.maquina.domain.exceptions.MaquinaNoAptaProduccionException;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.ConfigProduccionNoValidaException;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.ProductoActivoNoAsignadoException;
import org.springframework.stereotype.Service;

@Service
public class ValidarProcesoProduccionService {
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;
    private final EstacionOcupadaService estacionOcupadaService;

    public ValidarProcesoProduccionService(ObtenerConfigProduccionService obtenerConfigProduccionService,
                                           EstacionOcupadaService estacionOcupadaService) {
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
        this.estacionOcupadaService = estacionOcupadaService;
    }

    public void validar(Maquina maquina) {
        if(!maquina.estaAptaProduccion()) {
            throw new MaquinaNoAptaProduccionException("Estado maquina: " + maquina.getEstado() +
                    " no permite asignación de producto activo.");
        }

        if(!maquina.tieneProductoActivo()) {
            throw new ProductoActivoNoAsignadoException("Producto activo no asignado.");
        }

        if(estacionOcupadaService.estaOcupada(maquina.getEstacion())) {
            throw new EstacionOcupadaException("Estación ocupada.");
        }

        if(!obtenerConfigProduccionService.obtenerPorProducto(maquina.getProductoActivo()).esValida()) {
            throw new ConfigProduccionNoValidaException("Configuración para producción no válida.");
        }
    }
}