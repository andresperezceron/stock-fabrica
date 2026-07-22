package com.fabrica.stock.producto.configinyeccion.infrastructure;

import com.fabrica.stock.producto.configinyeccion.domain.ConfigInyeccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfigInyeccionRepository extends JpaRepository<ConfigInyeccion,Long> {
    boolean existsConfigProduccionByProductoId(Long productoId);
    Optional<ConfigInyeccion> findConfigProduccionByProductoId(Long productoId);
}