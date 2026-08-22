package com.fabrica.stock.entradaforanea.application;

import com.fabrica.stock.entradaforanea.api.dto.PaletCajasVacioResponse;
import com.fabrica.stock.estacion.application.ObtenerEstacionService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.TipoPalet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

@Service
public class CrearPaletCajasVacioService {
    private final ObtenerProductoService obtenerProductoService;
    private final ObtenerEstacionService obtenerEstacionService;
    private final PaletRepository paletRepository;

    public CrearPaletCajasVacioService(ObtenerProductoService obtenerProductoService,
                                       ObtenerEstacionService obtenerEstacionService,
                                       PaletRepository paletRepository)
    {
        this.obtenerProductoService = obtenerProductoService;
        this.obtenerEstacionService = obtenerEstacionService;
        this.paletRepository = paletRepository;
    }

    public PaletCajasVacioResponse crear(Long productoId) {
        Producto producto = obtenerProductoService.obtener(productoId);
        return PaletCajasVacioResponse.of(paletRepository.save(new Palet(
                TipoPalet.CAJA,
                producto,
                obtenerEstacionService.obtenerProveedor()))
        );
    }
}