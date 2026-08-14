package com.fabrica.stock.maquina.api;

import com.fabrica.stock.maquina.api.dto.IniciarProduccionRequest;
import com.fabrica.stock.maquina.api.dto.PaletsMateriaPrimaResponse;
import com.fabrica.stock.maquina.api.dto.ProductosIdDescripResponse;
import com.fabrica.stock.maquina.application.*;
import com.fabrica.stock.maquina.domain.EstadoMaquina;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maquinas")
public class MaquinaController {
    private final AsignarProductoActivoService asignarProductoActivoService;
    private final CambiarEstadoMaquinaService cambiarEstadoMaquinaService;
    private final IniciarProduccionService iniciarProduccionService;
    private final ListarProductoActivoService listarProductoActivoService;
    private final ListarAgregarMateriaPrimaService listarAgregarMateriaPrimaService;

    public MaquinaController(AsignarProductoActivoService asignarProductoActivoService,
                             CambiarEstadoMaquinaService cambiarEstadoMaquinaService,
                             IniciarProduccionService iniciarProduccionService,
                             ListarProductoActivoService listarProductoActivoService,
                             ListarAgregarMateriaPrimaService listarAgregarMateriaPrimaService)
    {
        this.asignarProductoActivoService = asignarProductoActivoService;
        this.cambiarEstadoMaquinaService = cambiarEstadoMaquinaService;
        this.iniciarProduccionService = iniciarProduccionService;
        this.listarProductoActivoService = listarProductoActivoService;
        this.listarAgregarMateriaPrimaService = listarAgregarMateriaPrimaService;
    }

    @GetMapping("/{id}/producto-activo")
    public ResponseEntity<ProductosIdDescripResponse> listarProductoActivo(@PathVariable Long id) {
        List<Producto> productos = listarProductoActivoService.listar(id);
        return ResponseEntity.ok(ProductosIdDescripResponse.of(productos));
    }

    @GetMapping("/{id}/materia-prima")
    public ResponseEntity<PaletsMateriaPrimaResponse> listarMateriaPrima(@PathVariable Long id) {
        List<Palet> palets = listarAgregarMateriaPrimaService.listar(id);
        return ResponseEntity.ok(PaletsMateriaPrimaResponse.of(palets));
    }

    @PostMapping("/iniciar")
    public ResponseEntity<Void> iniciarProduccion(@Valid @RequestBody IniciarProduccionRequest request) {
        iniciarProduccionService.iniciar(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/asignar/{productoActivoId}")
    public ResponseEntity<Void> asignarProductoActivo(
            @PathVariable Long id,
            @PathVariable Long productoActivoId) {
        asignarProductoActivoService.asignar(id, productoActivoId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/fuera-servicio")
    public ResponseEntity<Void> asignarEstadoFueraServicio(@PathVariable Long id) {
        cambiarEstadoMaquinaService.cambiar(id, EstadoMaquina.FUERA_SERVICIO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/mantenimiento")
    public ResponseEntity<Void> asignarEstadoMantenimiento(@PathVariable Long id) {
        cambiarEstadoMaquinaService.cambiar(id, EstadoMaquina.MANTENIMIENTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/cambio-molde")
    public ResponseEntity<Void> asignarEstadoCambioMolde(@PathVariable Long id) {
        cambiarEstadoMaquinaService.cambiar(id, EstadoMaquina.CAMBIO_MOLDE);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/apta-produccion")
    public ResponseEntity<Void> asignarEstadoAptaProduccion(@PathVariable Long id) {
        cambiarEstadoMaquinaService.cambiar(id, EstadoMaquina.APTA_PRODUCCION);
        return ResponseEntity.noContent().build();
    }
}