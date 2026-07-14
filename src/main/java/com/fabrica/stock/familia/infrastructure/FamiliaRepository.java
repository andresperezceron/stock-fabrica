package com.fabrica.stock.familia.infrastructure;

import com.fabrica.stock.familia.domain.Familia;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FamiliaRepository extends JpaRepository<Familia, Long> {
    Optional<Ubicacion> findByNombre(String nombreFamilia);
    boolean existsByNombre(String nombreFamilia);
}
