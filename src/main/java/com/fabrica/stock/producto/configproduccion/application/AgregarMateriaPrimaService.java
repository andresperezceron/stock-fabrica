package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.ConfigProduccionNoExisteException;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.MateriaPrimaDupicadaException;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.MateriaPrimaNoValidaException;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class AgregarMateriaPrimaService {
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;

    public AgregarMateriaPrimaService(ObtenerConfigProduccionService obtenerConfigProduccionService) {
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
    }

    public void agregar(Producto producto, Producto materiaPrima) {
        ConfigProduccion config = obtenerConfigProduccionService.obtenerPorProducto(producto);
        if(!materiaPrima.esMateriaPrima()) throw new MateriaPrimaNoValidaException("El producto no es materia prima.");

        boolean duplicada = config.getMateriasPrimas()
                .stream()
                .anyMatch(mp -> mp.getId().equals(materiaPrima.getId()));
        if(duplicada) throw new MateriaPrimaDupicadaException("La materia prima ya está agregada.");

        config.agregarMateriaPrima(materiaPrima);
    }
}