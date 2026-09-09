package com.fabrica.stock.consumoinyeccion.application;

import com.fabrica.stock.movimiento.application.CrearMovimientoService;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AgregarPaletService {
    private final ObtenerPaletService obtenerPaletService;
    private final CrearMovimientoService crearMovimientoService;

    public AgregarPaletService(ObtenerPaletService obtenerPaletService,
                               CrearMovimientoService crearMovimientoService)
    {
        this.obtenerPaletService = obtenerPaletService;
        this.crearMovimientoService = crearMovimientoService;
    }

    @Transactional
    public void agregarPalet(Long paletId) {
        Palet palet = obtenerPaletService.obtener(paletId);

        if(!palet.soyConsumoInyeccion())
            throw new RecursoNoValidoException("Palet no valido para consumo inyección.");

        crearMovimientoService.consumoInyeccion(palet);
    }
}