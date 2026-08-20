package com.fabrica.stock.palet.application;

import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.palet.api.dto.response.VerPaletResponse;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VerPaletService {
    private final ObtenerPaletService obtenerPaletService;

    public VerPaletService(ObtenerPaletService obtenerPaletService) {
        this.obtenerPaletService = obtenerPaletService;
    }

    public VerPaletResponse ver(Long paletId) {
        Palet palet = obtenerPaletService.obtener(paletId);

        if(!palet.estoyPendienteValidacion() && !palet.estoyValidado())
            throw new EstadoNoValidoException("Servicio no disponible.");

        List<ContenidoPalet> consolidados = new ArrayList<>();

        if(palet.soyTipoGranel()) consolidados.add(palet.getContenidoGranel());
        else for(ContenidoPalet contenido : palet.getContenidos()) {
                ContenidoPalet existente = consolidados.stream()
                        .filter(c -> c.esMismoLoteTipoCaja(contenido))
                        .findFirst()
                        .orElse(null);
                if(existente == null) consolidados.add(new ContenidoPalet(
                        contenido.getPalet(),
                        contenido.getLote(),
                        contenido.getTipo(),
                        contenido.getCantidad(),
                        contenido.getLotesMateriaPrima()
                ));
                else existente.acumular(contenido);
            }

        return VerPaletResponse.of(palet, consolidados);
    }
}