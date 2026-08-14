package com.fabrica.stock.lote.infrastructure;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.vo.CodigoLote;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoteRepository extends JpaRepository<Lote, Long> {
    boolean existsByProducto(Producto producto);
    Optional<Lote> findByCodigo(CodigoLote codigo);
}
