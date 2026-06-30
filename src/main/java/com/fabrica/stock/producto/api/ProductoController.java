package com.fabrica.stock.producto.api;


import com.fabrica.stock.producto.api.dto.CrearProductoRequest;
import com.fabrica.stock.producto.application.CrearProductoService;
import com.fabrica.stock.producto.application.ListarProductoService;
import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final CrearProductoService crearProductoService;
    private final ListarProductoService listarProductoService;
    private final ObtenerProductoService obtenerProductoService;

    public ProductoController(CrearProductoService crearProductoService,
                              ListarProductoService listarProductoService,
                              ObtenerProductoService obtenerProductoService)
    {
        this.crearProductoService = crearProductoService;
        this.listarProductoService = listarProductoService;
        this.obtenerProductoService = obtenerProductoService;
    }

    @PostMapping
    public Producto crear(@Valid @RequestBody CrearProductoRequest request) {
        return crearProductoService.ejecutar(request);
    }

    @PostMapping("/{id}")
    public Producto obtener(@PathVariable Long id) {
        return obtenerProductoService.obtener(id);
    }

    @GetMapping
    public List<Producto> listar() { return listarProductoService.listar(); }

}