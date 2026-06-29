package com.fabrica.stock.producto.infrastructure;

import com.fabrica.stock.producto.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    boolean existsByCodigo(String codigo);
    Optional<Producto> findByCodigo(Producto producto);
}