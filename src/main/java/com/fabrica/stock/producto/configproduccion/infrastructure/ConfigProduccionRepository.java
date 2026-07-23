package com.fabrica.stock.producto.configproduccion.infrastructure;

import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigProduccionRepository extends JpaRepository<ConfigProduccion,Long> {
    ConfigProduccion findConfigProduccionByProducto(Producto producto);
    boolean existsConfigProduccionByProducto(Producto producto);
}