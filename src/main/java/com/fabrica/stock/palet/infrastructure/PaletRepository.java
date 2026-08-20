package com.fabrica.stock.palet.infrastructure;

import com.fabrica.stock.palet.domain.EstadoPalet;
import com.fabrica.stock.palet.domain.Palet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaletRepository extends JpaRepository<Palet, Long> {
    List<Palet> findAllByEstado(EstadoPalet estado);
}