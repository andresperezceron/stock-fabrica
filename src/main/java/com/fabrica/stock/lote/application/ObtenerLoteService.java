package com.fabrica.stock.lote.application;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.infrastructure.LoteRepository;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoEncontradoException;
import org.springframework.stereotype.Service;

@Service
public class ObtenerLoteService {
    private final LoteRepository loteRepository;

    public ObtenerLoteService(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public Lote obtener(Long id) {
        return loteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe Lote con maquinaId: " + id));
    }
}
