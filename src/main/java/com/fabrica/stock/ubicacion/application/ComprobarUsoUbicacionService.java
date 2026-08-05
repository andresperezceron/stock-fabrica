package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.movimiento.infrastructure.MovimientoRepository;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import org.springframework.stereotype.Service;

@Service
public class ComprobarUsoUbicacionService {
    private final MovimientoRepository movimientoRepository;
    private final PaletRepository paletRepository;

    public ComprobarUsoUbicacionService(MovimientoRepository movimientoRepository,
                                        PaletRepository paletRepository)
    {
        this.movimientoRepository = movimientoRepository;
        this.paletRepository = paletRepository;
    }

    public boolean tieneUso(Ubicacion ubicacion) {
        return movimientoRepository.existsByOrigen(ubicacion)
                || movimientoRepository.existsByDestino(ubicacion);
    }
}