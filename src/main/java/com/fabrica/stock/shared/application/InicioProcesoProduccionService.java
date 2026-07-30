package com.fabrica.stock.shared.application;

import com.fabrica.stock.estacion.application.EstacionOcupadaService;
import com.fabrica.stock.estacion.domain.exceptions.EstacionOcupadaException;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.maquina.domain.exceptions.MaquinaNoAptaProduccionException;
import com.fabrica.stock.maquina.domain.exceptions.ProductoActivoNoValidoException;
import com.fabrica.stock.producto.application.ConfigProduccionService;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.ConfigProduccionNoValidaException;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.ProductoActivoNoAsignadoException;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class InicioProcesoProduccionService {
    private final EstacionOcupadaService estacionOcupadaService;
    private final ConfigProduccionService configProduccionService;

    public InicioProcesoProduccionService(EstacionOcupadaService estacionOcupadaService,
                                          ConfigProduccionService configProduccionService)
    {
        this.estacionOcupadaService = estacionOcupadaService;
        this.configProduccionService = configProduccionService;
    }

    public void validarCreacionDePalet(Maquina maquina) {
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

        if(!configProduccionService.esValida(maquina.getProductoActivo())) {
            throw new ConfigProduccionNoValidaException("Configuración para producción no válida."); }
    }

    public void validarAsignarProductoActivo(Maquina maquina, Producto producto) {
        if(!maquina.estaAptaProduccion()) {
            throw new MaquinaNoAptaProduccionException("Estado maquina: " + maquina.getEstado() +
                    " no permite asignación de producto activo.");
        }

        if(estacionOcupadaService.estaOcupada(maquina.getEstacion())) {
            throw new EstacionOcupadaException("Estación ocupada.");
        }

        if(!maquina.getTipo().puedeFabricar(producto)) {
            throw new ProductoActivoNoValidoException("Producto incompatible con la maquina: " + maquina.getNombre());
        }
    }
}