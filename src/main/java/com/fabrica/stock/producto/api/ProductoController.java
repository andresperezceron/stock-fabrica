package com.fabrica.stock.producto.api;


import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.application.CrearProductoService;
import com.fabrica.stock.producto.application.ListarProductoService;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final CrearProductoService crearProductoService;
    private final ListarProductoService listarProductoService;

    public ProductoController(CrearProductoService crearProductoService, ListarProductoService listarProductoService) {
        this.crearProductoService = crearProductoService;
        this.listarProductoService = listarProductoService;
    }

    @PostMapping
    public Producto crear(@Valid @RequestBody ProductoRequest request) {
        return crearProductoService.ejecutar(request);
    }

    @GetMapping("/listar")
    public List<Producto> listar() { return listarProductoService.ejecutar(); }
}