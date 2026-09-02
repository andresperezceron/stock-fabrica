package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.api.dto.response.TerminalResponse;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.application.PaletsConsumoInyeccionService;
import com.fabrica.stock.producto.api.dto.ProductosInyeccionResponse;
import com.fabrica.stock.producto.application.ListarProductosInyectoraService;
import org.springframework.stereotype.Service;

@Service
public class TerminalService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ListarProductosInyectoraService listarProductosInyectoraService;
    private final PaletsConsumoInyeccionService paletsConsumoInyeccionService;

    public TerminalService(ObtenerMaquinaService obtenerMaquinaService,
                           ListarProductosInyectoraService listarProductosInyectoraService,
                           PaletsConsumoInyeccionService paletsConsumoInyeccionService)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.listarProductosInyectoraService = listarProductosInyectoraService;
        this.paletsConsumoInyeccionService = paletsConsumoInyeccionService;
    }

    public TerminalResponse obtener(Long maquinaId) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        return TerminalResponse.of(
                maquina,
                ProductosInyeccionResponse.of(listarProductosInyectoraService.listar()),
                paletsConsumoInyeccionService.listar());
    }
}