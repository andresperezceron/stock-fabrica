package com.fabrica.stock.palet.application;

import com.fabrica.stock.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.api.dto.RegistrarProduccionRequest;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.TipoCaja;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;
import org.springframework.stereotype.Service;

@Service
public class ComprobarRegProduccionService {
    public void comprobar(Maquina maquina, Palet palet, ConfigProduccion config, RegistrarProduccionRequest request) {

        if(request.cantidad() == 0)
            throw new RecursoNoValidoException("Contenido mínimo una 1 caja.");

        if(!maquina.estaEnProduccion())
            throw new EstadoNoValidoException("Servicio no disponible.");

        if(!palet.getMaquina().getId().equals(maquina.getId()))
            throw new RecursoNoValidoException("Palet no pertenece a la máquina.");

        if(!palet.estaAbierto() && !palet.estaCerrado())
            throw new RecursoNoValidoException("Palet no pertenece a producción activa.");

        if(palet.estaCerrado())
            throw new EstadoNoValidoException("Palet está cerrado.");

        if((request.tipo() == TipoCaja.MEDIA ? request.cantidad() / 2 : request.cantidad())
                + palet.capacidadOcupada() > config.getCajasPorPalet())
            throw new RecursoNoValidoException("Registro supera cajas por palet.");
    }
}