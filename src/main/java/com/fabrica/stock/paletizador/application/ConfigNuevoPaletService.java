package com.fabrica.stock.paletizador.application;

import com.fabrica.stock.paletizador.api.dto.NuevoPaletRequest;
import com.fabrica.stock.paletizador.api.dto.NuevoPaletResponse;
import com.fabrica.stock.palet.application.CrearPaletService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.ubicacion.application.ObtenerUbicacionService;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import org.springframework.stereotype.Service;

@Service
public class ConfigNuevoPaletService {
    private final ObtenerUbicacionService obtenerUbicacionService;
    private final CrearPaletService crearPaletService;

    public ConfigNuevoPaletService(ObtenerUbicacionService ubicacionService,
                                   CrearPaletService crearPaletService) {
        this.obtenerUbicacionService = ubicacionService;
        this.crearPaletService = crearPaletService;
    }

    public NuevoPaletResponse crearPalet(NuevoPaletRequest request){
        Ubicacion ubicacion = obtenerUbicacionService.obtener(request.ubicacionId);
        Palet palet = crearPaletService.crear(ubicacion, request.tipoPalet);
        return new NuevoPaletResponse(palet.getId());
    }
}