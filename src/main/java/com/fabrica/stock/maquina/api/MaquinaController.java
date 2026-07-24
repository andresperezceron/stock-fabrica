package com.fabrica.stock.maquina.api;

import com.fabrica.stock.maquina.application.ListarProductosInyecExtruService;
import com.fabrica.stock.producto.api.dto.ProductosInyecExtruResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/maquinas")
public class MaquinaController {
    private final ListarProductosInyecExtruService listarProductosInyecExtruService;

    public MaquinaController(ListarProductosInyecExtruService listarProductosInyecExtruService) {
        this.listarProductosInyecExtruService = listarProductosInyecExtruService;
    }

    @GetMapping
    public ResponseEntity<ProductosInyecExtruResponse> obtenerProductosInyecExtru() {
        return ResponseEntity.ok(listarProductosInyecExtruService.listar());
    }
}