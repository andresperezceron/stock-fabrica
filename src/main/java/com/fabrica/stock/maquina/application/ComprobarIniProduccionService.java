package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import org.springframework.stereotype.Service;

@Service
public class ComprobarIniProduccionService {
    public void comprobar(Maquina maquina) {

        if(maquina.estaEnProduccion())
            throw new EstadoNoValidoException("Servicio no disponible.");

        if(!maquina.estaConfigurada())
            throw new EstadoNoValidoException("Máquina no configurada.");
    }
}