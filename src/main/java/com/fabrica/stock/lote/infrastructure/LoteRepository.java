package com.fabrica.stock.lote.infrastructure;

import com.fabrica.stock.lote.domain.Lote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoteRepository extends JpaRepository<Lote, Long> {
    List<Lote> findByProductoId(Long productoId);
    boolean existsByCodigoLote(String codigoLote);
}
