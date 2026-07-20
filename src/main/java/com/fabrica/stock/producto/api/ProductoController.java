package com.fabrica.stock.producto.api;

import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.application.*;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final CrearProductoService crearProductoService;
    private final ListarProductosService listarProductosService;
    private final ObtenerProductoService obtenerProductoService;
    private final ActualizarProductoService actualizarProductoService;
    private final EliminarProductoService eliminarProductoService;

    public ProductoController(CrearProductoService crearProductoService,
                              ListarProductosService listarProductosService,
                              ObtenerProductoService obtenerProductoService,
                              ActualizarProductoService actualizarProductoService,
                              EliminarProductoService eliminarProductoService)
    {
        this.crearProductoService = crearProductoService;
        this.listarProductosService = listarProductosService;
        this.obtenerProductoService = obtenerProductoService;
        this.actualizarProductoService = actualizarProductoService;
        this.eliminarProductoService = eliminarProductoService;
    }

    @GetMapping
    public List<Producto> listar() { return listarProductosService.listar(); }

    @GetMapping("/{id}")
    public Producto obtener(@PathVariable Long id) {
        return obtenerProductoService.obtener(id);
    }

    @PostMapping
    public void crear(@Valid @RequestBody ProductoRequest request) {
        crearProductoService.crear(request);
    }

    @PutMapping("/{id}")
    public void actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequest request) {
        actualizarProductoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { eliminarProductoService.eliminar(id); }
}