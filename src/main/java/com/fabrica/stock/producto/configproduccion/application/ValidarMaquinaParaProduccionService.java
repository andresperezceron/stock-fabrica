package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;

public class ValidarMaquinaParaProduccionService {
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;

    public ValidarMaquinaParaProduccionService(ObtenerConfigProduccionService obtenerConfigProduccionService) {
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
    }

    public void validar(Maquina maquina) {
        if(!maquina.tieneProductoActivo()) {
            //aqui lanzamos una excepcion.
        }

        //aqui lanzariamos otra excepcion si config no existe.
        ConfigProduccion config = obtenerConfigProduccionService.obtenerPorProducto(maquina.getProductoActivo());

        if(!config.esValida()) {
            //aqui lanzariamos otra excepcion.
        }
    }
}