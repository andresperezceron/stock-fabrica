package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;
import org.springframework.stereotype.Service;

@Service
public class ComprobarAsignarProActService {
    public void comprobar(Maquina maquina, Producto producto) {

        if(maquina.estaMantenimiento() || maquina.estaCambioMolde() || maquina.estaFueraServicio())
            throw new EstadoNoValidoException("Servicio no disponible.");

        if(maquina.estaEnProduccion())
            throw new EstadoNoValidoException("Servicio no disponible.");

        if(!maquina.getTipo().puedeFabricar(producto))
            throw new RecursoNoValidoException("Máquina no puede fabricar: " + producto.getDescripcion());
    }
}