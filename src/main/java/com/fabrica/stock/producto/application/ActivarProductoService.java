package com.fabrica.stock.producto.application;

import com.fabrica.stock.producto.domain.Producto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ActivarProductoService {
    private final ObtenerProductoInactivoService obtenerProductoInactivoService;

    public ActivarProductoService(ObtenerProductoInactivoService obtenerProductoInactivoService) {
        this.obtenerProductoInactivoService = obtenerProductoInactivoService;
    }

    @Transactional
    public void activar(Long id) {
        Producto producto = obtenerProductoInactivoService.obtener(id);
        producto.activar();
    }
}