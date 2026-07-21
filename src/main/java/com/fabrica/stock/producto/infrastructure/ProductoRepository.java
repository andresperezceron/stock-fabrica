package com.fabrica.stock.producto.infrastructure;

import com.fabrica.stock.producto.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    boolean existsByCodigo(String codigo);
    boolean existsByCodigoAndIdNot(String codigo, Long id);
    Optional<Producto> findByIdAndActivoTrue(Long id);
    Optional<Producto> findByIdAndActivoFalse(Long id);
    Optional<Producto> findByCodigoAndActivoTrue(String  codigo);
    Optional<Producto> findByCodigoAndActivoFalse(String  codigo);
    List<Producto> findAllByActivoTrueOrderByDescripcionAsc();
    List<Producto> findAllByActivoFalseOrderByDescripcionDesc();
}