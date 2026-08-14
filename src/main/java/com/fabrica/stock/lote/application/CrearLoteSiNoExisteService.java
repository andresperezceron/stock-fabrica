package com.fabrica.stock.lote.application;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.lote.domain.vo.CodigoLote;
import com.fabrica.stock.lote.infrastructure.LoteRepository;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrearLoteSiNoExisteService {
    private final LoteRepository loteRepository;

    public CrearLoteSiNoExisteService(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public Lote crearSiNoExiste(OrigenLote origenLote, String loteProveedor, Maquina maquina, Producto producto) {
        Maquina.LoteGenerado loteGenerado = maquina.obtenerLoteGenerado();
        CodigoLote codigo = new CodigoLote(loteGenerado.getCodigo());

        Optional<Lote> existente = loteRepository.findByCodigo(codigo);
        return existente.orElseGet(() -> loteRepository.save(new Lote(
                codigo,
                origenLote,
                loteProveedor,
                loteGenerado.getFechaCreacion(),
                maquina,
                producto,
                loteGenerado.getTurnoProduccion()
        )));
    }
}