package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.ConfigProduccionNoValidaException;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.ProductoActivoNoAsignadoException;
import org.springframework.stereotype.Service;

@Service
public class ValidarMaquinaParaProduccionService {
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;

    public ValidarMaquinaParaProduccionService(ObtenerConfigProduccionService obtenerConfigProduccionService) {
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
    }

    public void validar(Maquina maquina) {
        if(!maquina.tieneProductoActivo()) {
            throw new ProductoActivoNoAsignadoException("Producto activo no asignado.");
        }

        if(!obtenerConfigProduccionService.obtenerPorProducto(maquina.getProductoActivo()).esValida()) {
            throw new ConfigProduccionNoValidaException("Configuración para producción no válida.");
        }
    }
}