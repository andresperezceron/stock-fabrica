package com.fabrica.stock.producto.api;

import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.api.dto.ProductoResponse;
import com.fabrica.stock.producto.api.dto.ProductosResponse;
import com.fabrica.stock.producto.application.*;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final CrearProductoService crearProductoService;
    private final ListarProductosService listarProductosService;
    private final ListarProductosInactivosService listarProductosInactivosService;
    private final ObtenerProductoService obtenerProductoService;
    private final ActualizarProductoService actualizarProductoService;
    private final EliminarProductoService eliminarProductoService;

    public ProductoController(CrearProductoService crearProductoService,
                              ListarProductosService listarProductosService,
                              ListarProductosInactivosService listarProductosInactivosService,
                              ObtenerProductoService obtenerProductoService,
                              ActualizarProductoService actualizarProductoService,
                              EliminarProductoService eliminarProductoService)
    {
        this.crearProductoService = crearProductoService;
        this.listarProductosService = listarProductosService;
        this.listarProductosInactivosService = listarProductosInactivosService;
        this.obtenerProductoService = obtenerProductoService;
        this.actualizarProductoService = actualizarProductoService;
        this.eliminarProductoService = eliminarProductoService;
    }

    @GetMapping
    public ResponseEntity<ProductosResponse> listar() {
        List<Producto> productos = listarProductosService.listar();
        return ResponseEntity.ok(ProductosResponse.of(productos));
    }

    @GetMapping("/inactivos")
    public ResponseEntity<ProductosResponse> listarInactivos() {
        List<Producto> productos = listarProductosInactivosService.listar();
        return ResponseEntity.ok(ProductosResponse.of(productos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtener(@PathVariable Long id) {
        Producto producto = obtenerProductoService.obtener(id);
        return ResponseEntity.ok(ProductoResponse.of(producto));
    }

    @PostMapping
    public ResponseEntity<Void> crear(@Valid @RequestBody ProductoRequest request) {
        crearProductoService.crear(request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequest request) {
        actualizarProductoService.actualizar(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        eliminarProductoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}