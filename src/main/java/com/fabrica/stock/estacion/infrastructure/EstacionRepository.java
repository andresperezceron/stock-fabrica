package com.fabrica.stock.estacion.infrastructure;

import com.fabrica.stock.estacion.domain.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacionRepository extends JpaRepository<Estacion, Long> {
    boolean existsByNombre(String nombre);
    boolean existsByEstacion(Estacion estacion);
}
