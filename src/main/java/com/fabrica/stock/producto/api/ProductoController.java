package com.fabrica.stock.producto.api;


import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.application.CrearProductoService;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.shared.domain.RecursoNoEncontradoException;
import com.fabrica.stock.ubicacion.application.ListarUbicacionesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final CrearProductoService crearProductoService;

    public ProductoController(CrearProductoService crearProductoService) {
        this.crearProductoService = crearProductoService;
    }

    @PostMapping
    public Producto crear(@RequestBody ProductoRequest request) {
        return crearProductoService.crear(request);
    }

    /*@GetMapping("/{id}")
    public Producto obtener(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado " + id));
    }*/
}