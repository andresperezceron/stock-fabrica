package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.NoAdmiteConfigProduccionException;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class AgregarMateriaPrimaService {
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;
    private final ObtenerProductoService obtenerProductoService;

    public AgregarMateriaPrimaService(ObtenerConfigProduccionService obtenerConfigProduccionService,
                                      ObtenerProductoService obtenerProductoService)
    {
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
        this.obtenerProductoService = obtenerProductoService;
    }

    public void agregar(Long productoId, Long materiaPrimaId) {
        Producto producto = obtenerProductoService.obtener(productoId);
        Producto materiaPrima = obtenerProductoService.obtener(materiaPrimaId);

        if(!producto.admiteConfigProduccion()) {
            throw new NoAdmiteConfigProduccionException("Producto: " + producto.getDescripcion() +
                    " no admite config producción");
        }
        obtenerConfigProduccionService
                .obtenerPorProducto(producto)
                .agregarMateriaPrima(materiaPrima);
    }
}