package com.fabrica.stock.maquina.api;

import com.fabrica.stock.maquina.application.*;
import com.fabrica.stock.maquina.domain.EstadoMaquina;
import com.fabrica.stock.producto.api.dto.ProductosInyecExtruResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maquinas")
public class MaquinaController {
    private final ListarProductosInyecExtruService listarProductosInyecExtruService;
    private final AsignarProductoActivoService asignarProductoActivoService;
    private final AsignarEstadoService asignarEstadoService;

    public MaquinaController(ListarProductosInyecExtruService listarProductosInyecExtruService,
                             AsignarProductoActivoService asignarProductoActivoService,
                             AsignarEstadoService asignarEstadoService)
    {
        this.listarProductosInyecExtruService = listarProductosInyecExtruService;
        this.asignarProductoActivoService = asignarProductoActivoService;
        this.asignarEstadoService = asignarEstadoService;
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

    @PutMapping("{id}/fuera-servicio")
    public ResponseEntity<Void> asignarEstadoFueraServicio(@PathVariable Long id) {
        asignarEstadoService.asignar(id, EstadoMaquina.FUERA_SERVICIO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/cambio-molde")
    public ResponseEntity<Void> asignarEstadoCambioMolde(@PathVariable Long id) {
        asignarEstadoService.asignar(id, EstadoMaquina.CAMBIO_MOLDE);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/apta-produccion")
    public ResponseEntity<Void> asignarEstadoAptaProduccion(@PathVariable Long id) {
        asignarEstadoService.asignar(id, EstadoMaquina.APTA_PRODUCCION);
        return ResponseEntity.noContent().build();
    }
}