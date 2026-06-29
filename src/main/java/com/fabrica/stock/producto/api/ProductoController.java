package com.fabrica.stock.producto.api;


import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.application.CrearProductoService;
import com.fabrica.stock.producto.domain.Producto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final CrearProductoService crearProductoService;

    public ProductoController(CrearProductoService crearProductoService) {
        this.crearProductoService = crearProductoService;
    }

    @PostMapping
    public Producto crear(@RequestBody ProductoRequest request) {
        return crearProductoService.ejecutar(request);
    }

    /*@GetMapping("/{id}")
    public Producto obtener(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado " + id));
    }*/
}