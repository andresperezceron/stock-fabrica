package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.shared.domain.NombreUbicacionDuplicadoException;
import com.fabrica.stock.shared.domain.NombreUbicacionMaxLengthException;
import com.fabrica.stock.shared.domain.NombreUbicacionNullException;
import com.fabrica.stock.ubicacion.api.dto.UbicacionRequest;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.UbicacionConstraints;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;
import org.springframework.stereotype.Service;

@Service
public class CrearUbicacionService {
    private final UbicacionRepository ubicacionRepository;

    public CrearUbicacionService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    public Ubicacion crear(UbicacionRequest request) {
        if(request.nombre == null){
            throw new NombreUbicacionNullException("Nombre Ubicacion no puede ser nulo.");
        }

        request.nombre = request.nombre.trim();

        if(request.nombre.isBlank()){
            throw  new NombreUbicacionNullException("Nombre Ubicacion es obligatorio.");
        }

        if(request.nombre.length() > UbicacionConstraints.NOMBRE) {
            throw new NombreUbicacionMaxLengthException("Nombre Ubicacion máximo 50 caracteres.");
        }

        if(ubicacionRepository.existsByNombre(request.nombre)){
            throw new NombreUbicacionDuplicadoException(
                    "Nombre Ubicacion duplicada. Ya existe una ubicacion con el nombre: " + request.nombre);
        }

        return ubicacionRepository.save(new Ubicacion(request.nombre));
    }
}