package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.api.dto.response.TerminalResponse;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.producto.api.dto.DtoProductoInyeccion;
import com.fabrica.stock.producto.api.dto.ProductosInyeccionResponse;
import com.fabrica.stock.producto.application.ListarProductosInyectoraService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ListarProductosInyectoraService listarProductosInyectoraService;

    public TerminalService(ObtenerMaquinaService obtenerMaquinaService,
                           ListarProductosInyectoraService listarProductosInyectoraService)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.listarProductosInyectoraService = listarProductosInyectoraService;
    }

    public TerminalResponse obtener(Long maquinaId) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        return TerminalResponse.of(maquina, ProductosInyeccionResponse.of(listarProductosInyectoraService.listar()));
    }
}