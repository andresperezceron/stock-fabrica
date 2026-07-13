package com.fabrica.stock.movimiento.infrastructure;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.movimiento.domain.MovimientoStock;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoStockRepository extends JpaRepository<MovimientoStock, Long> {
    List<MovimientoStock> findByLoteId(Long loteId);
    List<MovimientoStock> findAllByLoteOrderByFechaAsc(Lote lote);
    boolean existsByOrigen(Ubicacion origen);
    boolean existsByDestino(Ubicacion destino);
}
