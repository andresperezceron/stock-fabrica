package com.fabrica.stock.palet.infrastructure;

import com.fabrica.stock.palet.domain.Palet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaletRepository extends JpaRepository<Palet, Long> {
}
