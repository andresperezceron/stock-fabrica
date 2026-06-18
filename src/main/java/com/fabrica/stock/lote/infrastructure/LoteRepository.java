package com.fabrica.stock.lote.infrastructure;

import com.fabrica.stock.lote.domain.Lote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoteRepository extends JpaRepository<Lote, Long> {
}
