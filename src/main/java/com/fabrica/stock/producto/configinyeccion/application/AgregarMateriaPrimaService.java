package com.fabrica.stock.producto.configinyeccion.application;

import com.fabrica.stock.producto.configinyeccion.domain.ConfigInyeccion;
import com.fabrica.stock.producto.configinyeccion.domain.exceptions.MateriaPrimaDupicadaException;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class AgregarMateriaPrimaService {
    private final ObtenerConfigInyeccionService obtenerConfigInyeccionService;

    public AgregarMateriaPrimaService(ObtenerConfigInyeccionService obtenerConfigInyeccionService) {
        this.obtenerConfigInyeccionService = obtenerConfigInyeccionService;
    }

    public void agregar(Producto producto, Producto materiaPrima) {
        ConfigInyeccion config = obtenerConfigInyeccionService.obtenerPorProducto(producto);

        boolean duplicada = config.getMateriasPrimas()
                .stream()
                .anyMatch(mp -> mp.getId().equals(materiaPrima.getId()));

        if(duplicada) {
            throw new MateriaPrimaDupicadaException("La materia prima ya está agregada.");
        }
        config.agregarMateriaPrima(materiaPrima);
    }
}