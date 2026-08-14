package com.fabrica.stock.movimiento.infrastructure;

import com.fabrica.stock.movimiento.domain.Movimiento;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    boolean existsByOrigen(Ubicacion origen);
    boolean existsByDestino(Ubicacion destino);
}
