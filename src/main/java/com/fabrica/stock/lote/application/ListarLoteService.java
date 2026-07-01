package com.fabrica.stock.lote.application;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.infrastructure.LoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarLoteService {
    private final LoteRepository loteRepository;

    public ListarLoteService(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public List<Lote> listar() {
        return loteRepository.findAll();
    }
}