package com.fabrica.stock.estacion.infrastructure;

import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.ubicacion.domain.vo.NombreUbicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstacionRepository extends JpaRepository<Estacion, Long> {
    boolean existsByNombre(NombreUbicacion nombre);
    Optional<Estacion> findByNombre(NombreUbicacion nombre);
}