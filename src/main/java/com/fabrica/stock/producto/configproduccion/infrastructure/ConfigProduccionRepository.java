package com.fabrica.stock.producto.configproduccion.infrastructure;

import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfigProduccionRepository extends JpaRepository<ConfigProduccion,Long> {
    boolean existsConfigProduccionByProductoId(Long productoId);
}