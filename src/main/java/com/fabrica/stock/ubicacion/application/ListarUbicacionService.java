package com.fabrica.stock.ubicacion.application;

import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.UbicacionSistema;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarUbicacionService {
    private final UbicacionRepository ubicacionRepository;

    public ListarUbicacionService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    public List<Ubicacion> listar() {
        return ubicacionRepository.findAllByOrderByNombreAsc()
                .stream()
                .filter(x -> !UbicacionSistema.esUbicacionSistema(x.getNombre()))
                .toList();
    }
}