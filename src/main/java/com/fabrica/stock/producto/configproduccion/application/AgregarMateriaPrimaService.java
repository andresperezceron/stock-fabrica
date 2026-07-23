package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.MateriaPrimaDupicadaException;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class AgregarMateriaPrimaService {
    private final ObtenerOCrearConfigProduccionService obtenerOCrearConfigProduccionService;

    public AgregarMateriaPrimaService(ObtenerOCrearConfigProduccionService obtenerOCrearConfigProduccionService) {
        this.obtenerOCrearConfigProduccionService = obtenerOCrearConfigProduccionService;
    }

    public void agregar(Producto producto, Producto materiaPrima) {
        ConfigProduccion config = obtenerOCrearConfigProduccionService.obtenerOCrearPorProducto(producto);

        boolean duplicada = config.getMateriasPrimas()
                .stream()
                .anyMatch(mp -> mp.getId().equals(materiaPrima.getId()));

        if(duplicada) {
            throw new MateriaPrimaDupicadaException("La materia prima ya está agregada.");
        }
        config.agregarMateriaPrima(materiaPrima);
    }
}