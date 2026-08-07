package com.fabrica.stock.ubicacion.infrastructure;

import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.vo.NombreUbicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {
    boolean existsByNombre(NombreUbicacion nombre);
    boolean existsByNombreAndIdNot(NombreUbicacion nombre, Long id);
    List<Ubicacion> findAllByActivoTrueOrderByNombreAsc();
    List<Ubicacion> findAllByActivoFalseOrderByNombreAsc();
    Optional<Ubicacion> findByIdAndActivoTrue(Long id);
    Optional<Ubicacion> findByIdAndActivoFalse(Long id);
    Optional<Ubicacion> findByNombreAndActivoTrue(NombreUbicacion nombre);
    Optional<Ubicacion> findByNombreAndActivoFalse(NombreUbicacion nombre);
}