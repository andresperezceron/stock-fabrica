package com.fabrica.stock.producto.api;

import com.fabrica.stock.producto.api.dto.ActualizarObservacionesRequest;
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
    private final ActivarProductoService activarProductoService;
    private final ConfigProduccionService configProduccionService;

    public ProductoController(CrearProductoService crearProductoService,
                              ListarProductosService listarProductosService,
                              ListarProductosInactivosService listarProductosInactivosService,
                              ObtenerProductoService obtenerProductoService,
                              ActualizarProductoService actualizarProductoService,
                              EliminarProductoService eliminarProductoService,
                              ActivarProductoService activarProductoService,
                              ConfigProduccionService configProduccionService)
    {
        this.crearProductoService = crearProductoService;
        this.listarProductosService = listarProductosService;
        this.listarProductosInactivosService = listarProductosInactivosService;
        this.obtenerProductoService = obtenerProductoService;
        this.actualizarProductoService = actualizarProductoService;
        this.eliminarProductoService = eliminarProductoService;
        this.activarProductoService = activarProductoService;
        this.configProduccionService = configProduccionService;
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

    @PostMapping("{id}/activar")
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

    @PutMapping("/{maquinaId}/materias-primas/{materiaPrimaId}")
    public ResponseEntity<Void> agregarMateriaPrima(
            @PathVariable Long maquinaId,
            @PathVariable Long materiaPrimaId) {
        configProduccionService.agregarMateriaPrima(maquinaId, materiaPrimaId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/observaciones")
    public ResponseEntity<Void> actualizarObservaciones(
            @PathVariable Long id,
            @RequestBody ActualizarObservacionesRequest request) {
        configProduccionService.actualizarObservaciones(id, request);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{paletId}/cajas-por-palet/{cajasPorPalet}")
    public ResponseEntity<Void> actualizarCajasPorPalet(
            @PathVariable Long paletId,
            @PathVariable Integer cajasPorPalet) {
        configProduccionService.actualizarCajasPorPalet(paletId, cajasPorPalet);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{maquinaId}/materias-primas/{materiaPrimaId}")
    public ResponseEntity<Void> quitarMateriaPrima(
            @PathVariable Long maquinaId,
            @PathVariable Long materiaPrimaId) {
        configProduccionService.quitarMateriaPrima(maquinaId, materiaPrimaId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        eliminarProductoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}