package com.fabrica.stock.palet.infrastructure;

import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.domain.Palet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaletRepository extends JpaRepository<Palet, Long> {

    @Query("""
    SELECT COUNT(p) > 0
    FROM Palet p
    WHERE p.maquina = :maquina
      AND p.estado NOT IN (
            com.fabrica.stock.palet.domain.EstadoPalet.FINALIZADO,
            com.fabrica.stock.palet.domain.EstadoPalet.VALIDADO
      )
""")
    boolean existsProcesoProduccionActivo(@Param("maquina") Maquina maquina);

    @Query("""
    SELECT COUNT(p) > 0
    FROM Palet p
    WHERE p.localizacion = :estacion
""")
    boolean existsByLocalizacion(@Param("estacion") Estacion estacion);
}
