package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.exceptions.NombreUbicacionDuplicadoException;
import com.fabrica.stock.ubicacion.domain.vo.NombreUbicacion;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidarNombreUbicacionService {
    private final UbicacionRepository ubicacionRepository;

    public ValidarNombreUbicacionService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    public NombreUbicacion validar(String nombre) {
        NombreUbicacion nombreUbicacion = new NombreUbicacion(nombre);
        if(ubicacionRepository.existsByNombre(nombreUbicacion))
            throw new NombreUbicacionDuplicadoException("Nombre duplicado.");
        return nombreUbicacion;
    }

    public NombreUbicacion validar(String nombre, Ubicacion ubicacion) {
        NombreUbicacion nombreUbicacion = new NombreUbicacion(nombre);
        if(ubicacionRepository.existsByNombreAndIdNot(nombreUbicacion, ubicacion.getId()))
            throw new NombreUbicacionDuplicadoException("Nombre duplicado.");
        return nombreUbicacion;
    }
}