package com.fabrica.stock.palet.application;

import com.fabrica.stock.palet.api.dto.response.VerPaletDetalleResponse;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import org.springframework.stereotype.Service;

@Service
public class VerPaletDetalleService {
    private final ObtenerPaletService obtenerPaletService;

    public VerPaletDetalleService(ObtenerPaletService obtenerPaletService) {
        this.obtenerPaletService = obtenerPaletService;
    }

    public VerPaletDetalleResponse ver(Long paletId) {
        Palet palet = obtenerPaletService.obtener(paletId);

        if(!palet.estoyPendienteValidacion() && !palet.estoyValidado())
            throw new EstadoNoValidoException("Servicio no disponible.");

        return VerPaletDetalleResponse.of(palet);
    }
}
