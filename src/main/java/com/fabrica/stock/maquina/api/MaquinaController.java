package com.fabrica.stock.maquina.api;

import com.fabrica.stock.maquina.api.dto.IniciarProduccionRequest;
import com.fabrica.stock.maquina.application.*;
import com.fabrica.stock.maquina.domain.EstadoMaquina;
import com.fabrica.stock.producto.api.dto.ProductosInyecExtruResponse;
import com.fabrica.stock.shared.application.InicioProcesoProduccionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maquinas")
public class MaquinaController {
    private final ListarProductosInyecExtruService listarProductosInyecExtruService;
    private final AsignarProductoActivoService asignarProductoActivoService;
    private final CambiarEstadoMaquinaService cambiarEstadoMaquinaService;
    private final IniciarProduccionService iniciarProduccionService;

    public MaquinaController(ListarProductosInyecExtruService listarProductosInyecExtruService,
                             AsignarProductoActivoService asignarProductoActivoService,
                             CambiarEstadoMaquinaService cambiarEstadoMaquinaService,
                             IniciarProduccionService iniciarProduccionService)
    {
        this.listarProductosInyecExtruService = listarProductosInyecExtruService;
        this.asignarProductoActivoService = asignarProductoActivoService;
        this.cambiarEstadoMaquinaService = cambiarEstadoMaquinaService;
        this.iniciarProduccionService = iniciarProduccionService;
    }

    @GetMapping
    public ResponseEntity<ProductosInyecExtruResponse> obtenerProductosInyecExtru() {
        return ResponseEntity.ok(listarProductosInyecExtruService.listar());
    }

    @PostMapping("/iniciar")
    public ResponseEntity<Void> iniciarProduccion(@Valid @RequestBody IniciarProduccionRequest request) {
        iniciarProduccionService.iniciar(request);
        return ResponseEntity.noContent().build();
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
        cambiarEstadoMaquinaService.cambiar(id, EstadoMaquina.FUERA_SERVICIO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/fuera-servicio")
    public ResponseEntity<Void> asignarEstadoMantenimiento(@PathVariable Long id) {
        cambiarEstadoMaquinaService.cambiar(id, EstadoMaquina.MANTENIMIENTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/cambio-molde")
    public ResponseEntity<Void> asignarEstadoCambioMolde(@PathVariable Long id) {
        cambiarEstadoMaquinaService.cambiar(id, EstadoMaquina.CAMBIO_MOLDE);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}/apta-produccion")
    public ResponseEntity<Void> asignarEstadoAptaProduccion(@PathVariable Long id) {
        cambiarEstadoMaquinaService.cambiar(id, EstadoMaquina.APTA);
        return ResponseEntity.noContent().build();
    }
}