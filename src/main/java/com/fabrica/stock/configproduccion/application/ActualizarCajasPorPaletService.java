package com.fabrica.stock.configproduccion.application;

import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.configproduccion.domain.exceptions.CajasPorPaletNoValidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ActualizarCajasPorPaletService {
    @Transactional
    public void actualizar(Palet palet, Integer cajasPorPalet) {
        if(cajasPorPalet == null || cajasPorPalet <= 0) {
            throw new CajasPorPaletNoValidoException("Numero de cajas por palet no válido.");
        }
        if(palet.esTipoGranel() && cajasPorPalet != 1) {
            throw new CajasPorPaletNoValidoException("Panet granel piezas por caja solo pueder 1.");
        }
    }
}