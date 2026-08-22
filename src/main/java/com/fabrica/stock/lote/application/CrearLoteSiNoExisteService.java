package com.fabrica.stock.lote.application;

import com.fabrica.stock.lote.domain.GeneradorCodigoLote;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.vo.CodigoLote;
import com.fabrica.stock.lote.infrastructure.LoteRepository;
import com.fabrica.stock.maquina.domain.vo.NombreMaquina;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrearLoteSiNoExisteService {
    private final LoteRepository loteRepository;

    public CrearLoteSiNoExisteService(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public Lote crearSiNoExiste(String loteProveedor, NombreMaquina nombreMaquina, Producto producto) {
        GeneradorCodigoLote loteGenerado = loteProveedor == null ?
                new GeneradorCodigoLote(nombreMaquina) :
                new GeneradorCodigoLote(new NombreMaquina("V"));

        CodigoLote codigo = new CodigoLote(loteGenerado.getCodigo());

        Optional<Lote> existente = loteRepository.findByCodigo(codigo);
        return existente.orElseGet(() -> loteRepository.save(new Lote(
                codigo,
                loteProveedor,
                loteGenerado.getFechaCreacion(),
                nombreMaquina,
                producto,
                loteGenerado.getTurnoProduccion()
        )));
    }
}