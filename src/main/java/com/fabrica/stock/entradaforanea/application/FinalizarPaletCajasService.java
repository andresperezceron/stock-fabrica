package com.fabrica.stock.entradaforanea.application;

import com.fabrica.stock.movimiento.application.CrearMovimientoService;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import com.fabrica.stock.shared.domain.exceptions.ConflictException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class FinalizarPaletCajasService {
    private final PaletRepository paletRepository;
    private final ObtenerPaletService obtenerPaletService;
    private final CrearMovimientoService crearMovimientoService;

    public FinalizarPaletCajasService(PaletRepository paletRepository,
                                      ObtenerPaletService obtenerPaletService,
                                      CrearMovimientoService crearMovimientoService)
    {
        this.paletRepository = paletRepository;
        this.obtenerPaletService = obtenerPaletService;
        this.crearMovimientoService = crearMovimientoService;
    }

    @Transactional
    public void finalizar(Long paletId) {
        Palet palet = obtenerPaletService.obtener(paletId);

        if(!palet.estoyEstacionProveedor())
            throw new ConflictException("Palet no está en Proveedor.");

        if(!palet.estoyVacio()) crearMovimientoService.entradaForanea(palet);
        else paletRepository.delete(palet);
    }
}