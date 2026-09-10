package com.fabrica.stock.mezcla.infrastructure;

import com.fabrica.stock.mezcla.domain.Mezcla;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MezclaRepository extends JpaRepository<Mezcla, Long> {
}
