package com.fabrica.stock.palet.application;

import com.fabrica.stock.palet.api.dto.CrearPaletRequest;
import com.fabrica.stock.palet.api.dto.CrearPaletResponse;
import com.fabrica.stock.palet.domain.ConfiguracionPalet;
import com.fabrica.stock.palet.domain.EstadoPalet;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import com.fabrica.stock.ubicacion.application.ObtenerUbicacionService;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;
import org.springframework.stereotype.Service;

@Service
public class CrearPaletService {
    private final PaletRepository paletRepository;
    private final ObtenerUbicacionService obtenerUbicacionService;

    public CrearPaletService(PaletRepository paletRepository,
                             ObtenerUbicacionService obtenerUbicacionService,
                             UbicacionRepository ubicacionRepository) {
        this.paletRepository = paletRepository;
        this.obtenerUbicacionService = obtenerUbicacionService;
    }

    public CrearPaletResponse crear(CrearPaletRequest request) {
        Palet palet = paletRepository.save(new Palet(
                obtenerUbicacionService.obtener(request.ubicacionId),
                ConfiguracionPalet.AUTOMATICO,
                EstadoPalet.ABIERTO));
        CrearPaletResponse crearPaletResponse = new CrearPaletResponse();
        crearPaletResponse.paletId = palet.getId();
        return crearPaletResponse;
    }
}