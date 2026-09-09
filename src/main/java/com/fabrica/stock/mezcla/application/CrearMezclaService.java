package com.fabrica.stock.mezcla.application;

import com.fabrica.stock.mezcla.domain.Mezcla;
import com.fabrica.stock.mezcla.infrastructure.MezclaRepository;
import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class CrearMezclaService {
    private final ObtenerProductoService obtenerProductoService;
    private final MezclaRepository mezclaRepository;

    public CrearMezclaService(ObtenerProductoService obtenerProductoService,
                              MezclaRepository mezclaRepository)
    {
        this.obtenerProductoService = obtenerProductoService;
        this.mezclaRepository = mezclaRepository;
    }

    public void crear(Long productoId) {
        Producto producto = obtenerProductoService.obtener(productoId);
        mezclaRepository.save(new Mezcla(producto));
    }
}