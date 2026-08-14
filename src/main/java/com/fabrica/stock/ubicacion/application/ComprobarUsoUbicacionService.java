package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.movimiento.infrastructure.MovimientoRepository;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import org.springframework.stereotype.Service;

@Service
public class ComprobarUsoUbicacionService {
    private final MovimientoRepository movimientoRepository;

    public ComprobarUsoUbicacionService(MovimientoRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }

    public boolean tieneUso(Ubicacion ubicacion) {
        return movimientoRepository.existsByOrigen(ubicacion) || movimientoRepository.existsByDestino(ubicacion);
    }
}