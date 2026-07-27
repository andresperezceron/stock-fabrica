package com.fabrica.stock.producto.application;

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
        obtenerProductoInactivoService.obtener(id).activar();
    }
}