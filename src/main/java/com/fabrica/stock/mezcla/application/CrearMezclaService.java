package com.fabrica.stock.mezcla.application;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.mezcla.domain.Mezcla;
import com.fabrica.stock.mezcla.infrastructure.MezclaRepository;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.shared.domain.exceptions.ConflictException;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrearMezclaService {
    private final ObtenerProductoService obtenerProductoService;
    private final ObtenerPaletService obtenerPaletService;
    private final MezclaRepository mezclaRepository;

    public CrearMezclaService(ObtenerProductoService obtenerProductoService,
                              ObtenerPaletService obtenerPaletService,
                              MezclaRepository mezclaRepository)
    {
        this.obtenerProductoService = obtenerProductoService;
        this.obtenerPaletService = obtenerPaletService;
        this.mezclaRepository = mezclaRepository;
    }

    public void crear(Long productoId, List<Long> paletsIds) {
        Producto producto = obtenerProductoService.obtener(productoId);
        List<Lote> lotes = new ArrayList<>();

        if(paletsIds == null) {
            throw new RecursoNoValidoException("Materias primas no puede ser null.");
        }

        if(paletsIds.isEmpty()) {
            throw new RecursoNoValidoException("Se necesita 1 o más materias primas para la creacion de la mezcla.");
        }

        for(Long paletId : paletsIds) {
            Palet palet = obtenerPaletService.obtener(paletId);

            if(!palet.estoyEstacionConsumoInyeccion()) {
                throw new RecursoNoValidoException("Palet no disponible para mezclas.");
            }

            Lote lote = palet.getContenidoGranel().getLote();

            if (lotes.stream().anyMatch(l -> l.getId().equals(lote.getId()))) {
                throw new ConflictException("Lote ya existe en la mezcla.");
            }

            lotes.add(lote);
        }
        mezclaRepository.save(new Mezcla(producto, lotes));
    }
}