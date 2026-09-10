package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.api.dto.response.TerminalResponse;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.application.PaletsConsumoInyeccionService;
import com.fabrica.stock.producto.api.dto.ProductosInyeccionResponse;
import com.fabrica.stock.producto.application.ProductosInyeccionService;
import org.springframework.stereotype.Service;

@Service
public class TerminalService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ProductosInyeccionService productosInyeccionService;
    private final PaletsConsumoInyeccionService paletsConsumoInyeccionService;

    public TerminalService(ObtenerMaquinaService obtenerMaquinaService,
                           ProductosInyeccionService productosInyeccionService,
                           PaletsConsumoInyeccionService paletsConsumoInyeccionService)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.productosInyeccionService = productosInyeccionService;
        this.paletsConsumoInyeccionService = paletsConsumoInyeccionService;
    }

    public TerminalResponse obtener(Long maquinaId) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        return TerminalResponse.of(
                maquina,
                productosInyeccionService.listar(),
                paletsConsumoInyeccionService.listar());
    }
}