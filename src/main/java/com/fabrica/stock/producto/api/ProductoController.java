package com.fabrica.stock.producto.api;

import com.fabrica.stock.producto.api.dto.ProductosInyeccionResponse;
import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.api.dto.DtoProducto;
import com.fabrica.stock.producto.api.dto.ProductosResponse;
import com.fabrica.stock.producto.application.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final CrearProductoService crearProductoService;
    private final ListarProductosService listarProductosService;
    private final ListarProductosInactivosService listarProductosInactivosService;
    private final ListarProductosInyectoraService listarProductosInyectoraService;
    private final ObtenerProductoService obtenerProductoService;
    private final ActualizarProductoService actualizarProductoService;
    private final EliminarProductoService eliminarProductoService;
    private final ActivarProductoService activarProductoService;

    public ProductoController(CrearProductoService crearProductoService,
                              ListarProductosService listarProductosService,
                              ListarProductosInactivosService listarProductosInactivosService,
                              ListarProductosInyectoraService listarProductosInyectoraService,
                              ObtenerProductoService obtenerProductoService,
                              ActualizarProductoService actualizarProductoService,
                              EliminarProductoService eliminarProductoService,
                              ActivarProductoService activarProductoService)
    {
        this.crearProductoService = crearProductoService;
        this.listarProductosService = listarProductosService;
        this.listarProductosInactivosService = listarProductosInactivosService;
        this.listarProductosInyectoraService = listarProductosInyectoraService;
        this.obtenerProductoService = obtenerProductoService;
        this.actualizarProductoService = actualizarProductoService;
        this.eliminarProductoService = eliminarProductoService;
        this.activarProductoService = activarProductoService;
    }

    @GetMapping
    public ResponseEntity<ProductosResponse> listar() {
        return ResponseEntity.ok(ProductosResponse.of(listarProductosService.listar()));
    }

    @GetMapping("/inactivos")
    public ResponseEntity<ProductosResponse> listarInactivos() {
        return ResponseEntity.ok(ProductosResponse.of(listarProductosInactivosService.listar()));
    }

    @GetMapping("/inyeccion")
    public ResponseEntity<ProductosInyeccionResponse> listarProductosInyeccion() {
        return ResponseEntity.ok(ProductosInyeccionResponse.of(listarProductosInyectoraService.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoProducto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(DtoProducto.of(obtenerProductoService.obtener(id)));
    }

    @PostMapping("/{id}/activar")
    public ResponseEntity<Void> activar(@PathVariable Long id) {
        activarProductoService.activar(id);
        return ResponseEntity.noContent().build();
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