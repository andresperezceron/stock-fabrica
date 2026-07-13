package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarUbicacionActivaService {
    private final UbicacionRepository ubicacionRepository;

    public ListarUbicacionActivaService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    public List<Ubicacion> listar() {
        return ubicacionRepository.findAllByActivoIsTrueOrderByNombreAsc();
    }
}