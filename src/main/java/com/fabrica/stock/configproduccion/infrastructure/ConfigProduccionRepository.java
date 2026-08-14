package com.fabrica.stock.configproduccion.infrastructure;

import com.fabrica.stock.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfigProduccionRepository extends JpaRepository<ConfigProduccion,Long> {
    Optional<ConfigProduccion> findConfigProduccionByProducto(Producto producto);
}