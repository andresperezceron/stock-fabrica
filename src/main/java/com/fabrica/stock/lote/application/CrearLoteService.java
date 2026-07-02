package com.fabrica.stock.lote.application;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.MaquinaProduccion;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.lote.infrastructure.LoteRepository;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CrearLoteService {
    private final LoteRepository loteRepository;
    private final GenerarLoteService generarLoteService;

    public CrearLoteService(LoteRepository loteRepository, GenerarLoteService generarLoteService) {
        this.loteRepository = loteRepository;
        this.generarLoteService = generarLoteService;
    }

    public Lote crear(Producto producto,
                      OrigenLote origenLote,
                      String loteProveedor,
                      MaquinaProduccion maquina,
                      LocalDateTime fecha)
    {
        return loteRepository.save(new Lote(
                generarLoteService.generar(maquina, fecha),
                origenLote,
                loteProveedor,
                producto,
                fecha
        ));
    }
}