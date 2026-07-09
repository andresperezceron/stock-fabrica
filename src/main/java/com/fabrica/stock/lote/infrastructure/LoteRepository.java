package com.fabrica.stock.lote.infrastructure;

import com.fabrica.stock.lote.domain.Lote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoteRepository extends JpaRepository<Lote, Long> {
    boolean existsByCodigoLote(String codigoLote);
    Optional<Lote> findByCodigoLote(String codigoLote);
}
