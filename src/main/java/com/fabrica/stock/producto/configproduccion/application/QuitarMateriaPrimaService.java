package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class QuitarMateriaPrimaService {
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;

    public QuitarMateriaPrimaService(ObtenerConfigProduccionService obtenerConfigProduccionService) {
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
    }

    @Transactional
    public void quitar(Maquina maquina, Producto materiaPrima) {
        ConfigProduccion config = obtenerConfigProduccionService.obtenerPorProducto(maquina.getProductoActivo());
        config.quitarMateriaPrima(materiaPrima);
        if(config.esValida()) maquina.configurada();
    }
}