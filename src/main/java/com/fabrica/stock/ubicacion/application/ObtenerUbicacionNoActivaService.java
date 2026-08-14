package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.shared.domain.exceptions.RecursoNoEncontradoException;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.vo.NombreUbicacion;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;
import org.springframework.stereotype.Service;

@Service
public class ObtenerUbicacionNoActivaService {
    private final UbicacionRepository ubicacionRepository;

    public ObtenerUbicacionNoActivaService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    public Ubicacion obtener(Long id) {
        return ubicacionRepository
                .findByIdAndActivoFalse(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe ubicación Inactiva con Id: " + id));
    }

    public Ubicacion obtener(NombreUbicacion nombre) {
        return ubicacionRepository
                .findByNombreAndActivoFalse(nombre)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("No existe ubicación Inactiva con nombre: "
                                + nombre.valor()));
    }
}