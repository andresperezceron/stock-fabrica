package com.fabrica.stock.estacion.infrastructure;

import com.fabrica.stock.estacion.domain.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstacionRepository extends JpaRepository<Estacion, Long> {
    boolean existsByNombre(String nombre);
    Optional<Estacion> findByNombre(String nombre);
}