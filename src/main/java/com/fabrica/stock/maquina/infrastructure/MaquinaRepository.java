package com.fabrica.stock.maquina.infrastructure;

import com.fabrica.stock.maquina.domain.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaquinaRepository extends JpaRepository<Maquina, Long> {

}
