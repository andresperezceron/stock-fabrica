package com.fabrica.stock.lote.application;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.MaquinaProduccion;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.lote.infrastructure.LoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CrearLoteService {
    private final LoteRepository loteRepository;

    public CrearLoteService(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public Lote crear(OrigenLote origenLote,
                      String loteProveedor,
                      MaquinaProduccion maquina,
                      LocalDateTime fecha)
    {
        LoteGenerado loteGenerado = new LoteGenerado(maquina, fecha);
        Optional<Lote> existente = loteRepository.findByCodigoLote(loteGenerado.getCodigoLote());

        return existente.orElseGet(() -> loteRepository.save(new Lote(
                loteGenerado.getCodigoLote(),
                origenLote,
                loteProveedor,
                fecha,
                maquina,
                loteGenerado.getTurno()
        )));
    }
}