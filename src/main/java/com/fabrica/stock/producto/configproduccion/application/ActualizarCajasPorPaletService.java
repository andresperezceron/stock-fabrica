package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.palet.domain.TipoPalet;
import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.CajasPorPaletNoValidoException;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ActualizarCajasPorPaletService {
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;

    public ActualizarCajasPorPaletService(ObtenerConfigProduccionService obtenerConfigProduccionService) {
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
    }

    @Transactional
    public void actualizar(Producto producto, Integer cajasPorPalet, TipoPalet tipoPalet) {
        ConfigProduccion config = obtenerConfigProduccionService.obtenerPorProducto(producto);
        if(cajasPorPalet == null || cajasPorPalet <= 0) {
            throw new CajasPorPaletNoValidoException("Numero de cajas por palet no válido.");
        }

        if(tipoPalet == TipoPalet.GRANEL && cajasPorPalet != 1) {
            throw new CajasPorPaletNoValidoException("Panet granel piezas por caja solo pueder 1.");
        }
    }
}