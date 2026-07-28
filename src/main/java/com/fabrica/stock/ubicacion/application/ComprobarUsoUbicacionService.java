package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.movimiento.infrastructure.MovimientoStockRepository;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import org.springframework.stereotype.Service;

@Service
public class ComprobarUsoUbicacionService {
    private final MovimientoStockRepository movimientoStockRepository;
    private final PaletRepository paletRepository;

    public ComprobarUsoUbicacionService(MovimientoStockRepository movimientoStockRepository,
                                        PaletRepository paletRepository)
    {
        this.movimientoStockRepository = movimientoStockRepository;
        this.paletRepository = paletRepository;
    }

    public boolean tieneUso(Ubicacion ubicacion) {
        return movimientoStockRepository.existsByOrigen(ubicacion)
                || movimientoStockRepository.existsByDestino(ubicacion);
    }
}