package com.fabrica.stock.contenidopalet.infrastructure;

import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContenidoPaletRepository extends JpaRepository<ContenidoPalet, Long> {
    boolean existsByProducto(Producto producto);
}
