package com.fabrica.stock.palet.application;

import com.fabrica.stock.palet.api.dto.AbrirPaletRequest;
import com.fabrica.stock.palet.api.dto.AbrirPaletResponse;
import com.fabrica.stock.palet.domain.EstadoPalet;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import com.fabrica.stock.ubicacion.application.ObtenerUbicacionService;
import org.springframework.stereotype.Service;

@Service
public class AbrirPaletService {
    private final ObtenerUbicacionService obtenerUbicacionService;
    private final PaletRepository paletRepository;

    public AbrirPaletService(ObtenerUbicacionService obtenerUbicacionService,
                             PaletRepository paletRepository)
    {
        this.obtenerUbicacionService = obtenerUbicacionService;
        this.paletRepository = paletRepository;
    }

    public AbrirPaletResponse abrir(AbrirPaletRequest request) {
        Palet palet = paletRepository.save(new Palet(
                obtenerUbicacionService.obtener(request.ubicacionId),
                EstadoPalet.ABIERTO));
        AbrirPaletResponse response = new AbrirPaletResponse();
        response.paletId = palet.getId();
        return response;
    }
}