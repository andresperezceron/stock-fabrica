package com.fabrica.stock.palet.infrastructure;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.domain.EstadoPalet;
import com.fabrica.stock.palet.domain.EstadoProcesoPalet;
import com.fabrica.stock.palet.domain.Palet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaletRepository extends JpaRepository<Palet, Long> {
    boolean existsByMaquinaAndEstado(Maquina maquina, EstadoPalet estado);
    Optional<Palet> findByMaquinaAndProceso(Maquina maquina, EstadoProcesoPalet estadoProceso);
}