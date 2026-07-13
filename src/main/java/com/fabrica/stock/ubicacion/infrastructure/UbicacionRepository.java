package com.fabrica.stock.ubicacion.infrastructure;

import com.fabrica.stock.ubicacion.domain.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {
    boolean existsByNombre(String nombreUbicacion);
    Optional<Ubicacion> findByNombre(String nombreUbicacion);
    List<Ubicacion> findAllByOrderByNombreAsc();
}
