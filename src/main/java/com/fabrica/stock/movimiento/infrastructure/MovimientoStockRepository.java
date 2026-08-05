package com.fabrica.stock.movimiento.infrastructure;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.movimiento.domain.Movimiento;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimientoStockRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByLoteId(Long loteId);
    List<Movimiento> findAllByLoteOrderByFechaAsc(Lote lote);
    boolean existsByOrigen(Ubicacion origen);
    boolean existsByDestino(Ubicacion destino);
    boolean existsByProducto(Producto producto);
}
