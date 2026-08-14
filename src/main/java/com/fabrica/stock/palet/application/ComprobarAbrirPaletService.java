package com.fabrica.stock.palet.application;

import com.fabrica.stock.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.shared.domain.exceptions.ConflictException;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;
import org.springframework.stereotype.Service;

@Service
public class ComprobarAbrirPaletService {

    public void comprobar(Maquina maquina, Palet palet, ConfigProduccion config) {

        if(!maquina.estaEnProduccion())
            throw new EstadoNoValidoException("Servicio no disponible.");

        if(!palet.getMaquina().getId().equals(maquina.getId()))
            throw new RecursoNoValidoException("Palet no pertenece a máquina.");

        if(!palet.estaAbierto() && !palet.estaCerrado())
            throw new RecursoNoValidoException("Palet no pertenece a producción activa.");

        if(!palet.estaCerrado())
            throw new EstadoNoValidoException("Palet no está cerrado.");

        if(!config.esValida())
            throw new ConflictException("Configuración producción no válida.") ;
    }
}