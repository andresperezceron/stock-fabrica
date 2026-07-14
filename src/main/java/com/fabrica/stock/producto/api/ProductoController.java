package com.fabrica.stock.producto.api;

import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.application.ActualizarProductoService;
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
    private final ActualizarProductoService actualizarProductoService;

    public ProductoController(CrearProductoService crearProductoService,
                              ListarProductoService listarProductoService,
                              ObtenerProductoService obtenerProductoService,
                              ActualizarProductoService actualizarProductoService)
    {
        this.crearProductoService = crearProductoService;
        this.listarProductoService = listarProductoService;
        this.obtenerProductoService = obtenerProductoService;
        this.actualizarProductoService = actualizarProductoService;
    }

    @GetMapping
    public List<Producto> listar() { return listarProductoService.listar(); }

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
}