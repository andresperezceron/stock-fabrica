package com.fabrica.stock.lote.application;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.MaquinaProduccion;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.lote.infrastructure.LoteRepository;
import com.fabrica.stock.maquina.domain.Maquina;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CrearLoteSiNoExisteService {
    private final LoteRepository loteRepository;

    public CrearLoteSiNoExisteService(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public Lote crearSiNoExiste(OrigenLote origenLote, String loteProveedor, Maquina maquina) {
        LoteGenerado loteGenerado = maquina.getGeneradorDeLotes();
        Optional<Lote> existente = loteRepository.findByCodigoLote(loteGenerado.getCodigoLote());

        return existente.orElseGet(() -> loteRepository.save(new Lote(
                loteGenerado.getCodigoLote(),
                origenLote,
                loteProveedor,
                loteGenerado.getFecha(),
                maquina,
                loteGenerado.getTurno()
        )));
    }
}