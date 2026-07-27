package com.fabrica.stock.maquina.api;

import com.fabrica.stock.maquina.application.AsignarProductoActivoService;
import com.fabrica.stock.maquina.application.ListarProductosInyecExtruService;
import com.fabrica.stock.producto.api.dto.ProductosInyecExtruResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maquinas")
public class MaquinaController {
    private final ListarProductosInyecExtruService listarProductosInyecExtruService;
    private final AsignarProductoActivoService asignarProductoActivoService;

    public MaquinaController(ListarProductosInyecExtruService listarProductosInyecExtruService,
                             AsignarProductoActivoService asignarProductoActivoService)
    {
        this.listarProductosInyecExtruService = listarProductosInyecExtruService;
        this.asignarProductoActivoService = asignarProductoActivoService;
    }

    @GetMapping
    public ResponseEntity<ProductosInyecExtruResponse> obtenerProductosInyecExtru() {
        return ResponseEntity.ok(listarProductosInyecExtruService.listar());
    }

    @PostMapping("{id}/asignar/{productoActivoId}")
    public ResponseEntity<Void> asignarProductoActivo(
            @PathVariable Long id,
            @PathVariable Long productoActivoId) {
        asignarProductoActivoService.asignar(id, productoActivoId);
        return ResponseEntity.noContent().build();
    }
}