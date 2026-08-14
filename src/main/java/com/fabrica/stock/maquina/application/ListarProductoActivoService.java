package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarProductoActivoService {
    private final ProductoRepository productoRepository;
    private final ObtenerMaquinaService obtenerMaquinaService;

    public ListarProductoActivoService(ProductoRepository productoRepository,
                                       ObtenerMaquinaService obtenerMaquinaService)
    {
        this.productoRepository = productoRepository;
        this.obtenerMaquinaService = obtenerMaquinaService;
    }

    public List<Producto> listar(Long maquinaId) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        return productoRepository
                .findAllByActivoTrueOrderByDescripcionAsc()
                .stream()
                .filter(maquina.getTipo()::puedeFabricar)
                .toList();
    }
}