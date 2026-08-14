package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.shared.domain.exceptions.RecursoNoEncontradoException;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.vo.NombreUbicacion;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;
import org.springframework.stereotype.Service;

@Service
public class ObtenerUbicacionService {
    private final UbicacionRepository ubicacionRepository;

    public ObtenerUbicacionService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    public Ubicacion obtener(Long id) {
        return ubicacionRepository
                .findByIdAndActivoTrue(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe ubicacion con id: " + id));
    }

    public Ubicacion obtener(NombreUbicacion nombre) {
        return ubicacionRepository
                .findByNombreAndActivoTrue(nombre)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("No existe ubicacion con nombre: " + nombre.valor()));
    }
}