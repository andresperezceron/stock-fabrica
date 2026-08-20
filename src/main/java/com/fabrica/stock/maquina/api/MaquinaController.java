package com.fabrica.stock.maquina.api;

import com.fabrica.stock.configproduccion.application.*;
import com.fabrica.stock.palet.api.dto.response.ContenidoRegistradoResponse;
import com.fabrica.stock.maquina.api.dto.request.*;
import com.fabrica.stock.maquina.api.dto.response.*;
import com.fabrica.stock.maquina.application.*;
import com.fabrica.stock.palet.api.dto.response.ContenidosRegistradosResponse;
import com.fabrica.stock.palet.application.ContenidoRegistradoService;
import com.fabrica.stock.palet.application.ContenidosRegistradosService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maquinas")
public class MaquinaController {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final CrearMaquinaService crearMaquinaService;
    private final AsignarConfigProduccionService asignarConfigProduccionService;
    private final CambiarEstadoMaquinaService cambiarEstadoMaquinaService;
    private final IniciarProduccionService iniciarProduccionService;
    private final ReanudarProduccionService reanudarProduccionService;
    private final FinalizarProduccionService finalizarProduccionService;
    private final RegistrarProduccionService registrarProduccionService;
    private final ActualizarCajasPorPaletService actualizarCajasPorPaletService;
    private final ActualizarObservacionesService actualizarObservacionesService;
    private final AgregarMateriaPrimaService agregarMateriaPrimaService;
    private final ConsumirMateriaPrimaService consumirMateriaPrimaService;
    private final ListarMaquinasService listarMaquinasService;
    private final ContenidoRegistradoService contenidoRegistradoService;
    private final ContenidosRegistradosService contenidosRegistradosService;
    private final VerConfigProduccionService verConfigProduccionService;

    public MaquinaController(ObtenerMaquinaService obtenerMaquinaService,
                             CrearMaquinaService crearMaquinaService,
                             AsignarConfigProduccionService asignarConfigProduccionService,
                             CambiarEstadoMaquinaService cambiarEstadoMaquinaService,
                             IniciarProduccionService iniciarProduccionService,
                             ReanudarProduccionService reanudarProduccionService,
                             FinalizarProduccionService finalizarProduccionService,
                             RegistrarProduccionService registrarProduccionService,
                             ActualizarCajasPorPaletService actualizarCajasPorPaletService,
                             ActualizarObservacionesService actualizarObservacionesService,
                             AgregarMateriaPrimaService agregarMateriaPrimaService,
                             ConsumirMateriaPrimaService consumirMateriaPrimaService,
                             ListarMaquinasService listarMaquinasService,
                             ContenidoRegistradoService contenidoRegistradoService,
                             ContenidosRegistradosService contenidosRegistradosService,
                             VerConfigProduccionService verConfigProduccionService)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.crearMaquinaService = crearMaquinaService;
        this.asignarConfigProduccionService = asignarConfigProduccionService;
        this.cambiarEstadoMaquinaService = cambiarEstadoMaquinaService;
        this.iniciarProduccionService = iniciarProduccionService;
        this.reanudarProduccionService = reanudarProduccionService;
        this.finalizarProduccionService = finalizarProduccionService;
        this.registrarProduccionService = registrarProduccionService;
        this.actualizarCajasPorPaletService = actualizarCajasPorPaletService;
        this.actualizarObservacionesService = actualizarObservacionesService;
        this.agregarMateriaPrimaService = agregarMateriaPrimaService;
        this.consumirMateriaPrimaService = consumirMateriaPrimaService;
        this.listarMaquinasService = listarMaquinasService;
        this.contenidoRegistradoService = contenidoRegistradoService;
        this.contenidosRegistradosService = contenidosRegistradosService;
        this.verConfigProduccionService = verConfigProduccionService;
    }

    @GetMapping
    public ResponseEntity<MaquinasResponse> listar() {
        return ResponseEntity.ok(MaquinasResponse.of(listarMaquinasService.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaquinaResponse> maquina(@PathVariable Long id) {
        return ResponseEntity.ok(MaquinaResponse.of(obtenerMaquinaService.obtener(id)));
    }

    @GetMapping("/{id}/palet/contenidos/{contenidoId}")
    public ResponseEntity<ContenidoRegistradoResponse> contenidoRegistrado(
            @PathVariable Long id,
            @PathVariable Long contenidoId) {
        return ResponseEntity.ok(contenidoRegistradoService.obtener(id, contenidoId));
    }

    @GetMapping("/{id}/palet/contenidos")
    public ResponseEntity<ContenidosRegistradosResponse> contenidosRegistrados(@PathVariable Long id) {
        return ResponseEntity.ok(contenidosRegistradosService.obtener(id));
    }

    @GetMapping("/{id}/config")
    public ResponseEntity<VerConfigProduccionResponse> verConfigProduccion(@PathVariable Long id) {
        return ResponseEntity.ok(verConfigProduccionService.ver(id));
    }

    @PostMapping
    public ResponseEntity<Void> crear(@Valid @RequestBody CrearMaquinaRequest request) {
        crearMaquinaService.crear(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/iniciar")
    public ResponseEntity<Void> iniciar(
            @PathVariable Long id,
            @Valid @RequestBody IniciarProduccionRequest request) {
        iniciarProduccionService.iniciar(id, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/reanudar")
    public ResponseEntity<Void> reanudar(@PathVariable Long id) {
        reanudarProduccionService.reanudar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/registrar")
    public ResponseEntity<Void> registrar(
            @PathVariable Long id,
            @Valid @RequestBody RegistrarProduccionRequest request) {
        registrarProduccionService.registrar(id, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/finalizar")
    public ResponseEntity<Void> finalizar(@PathVariable Long id) {
        finalizarProduccionService.finalizar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/config")
    public ResponseEntity<Void> asignarConfigProduccion(
            @PathVariable Long id,
            @Valid @RequestBody IdProductoRequest request) {
        asignarConfigProduccionService.asignar(id, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/config/materias-primas")
    public ResponseEntity<Void> agregarMateriaPrima(
            @PathVariable Long id,
            @Valid @RequestBody IdPaletMateriaPrimaRequest request) {
        agregarMateriaPrimaService.agregar(id, request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Void> asignarEstadoFueraServicio(
            @PathVariable Long id,
            @Valid @RequestBody EstadoMaquinaRequest request) {
        cambiarEstadoMaquinaService.cambiar(id, request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/config/cajas-por-palet")
    public ResponseEntity<Void> actualizarCajasPorPalet(
            @PathVariable Long id,
            @Valid @RequestBody CajasPorPaletRequest request) {
        actualizarCajasPorPaletService.actualizar(id, request);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/config/observaciones")
    public ResponseEntity<Void> actualizarObservaciones(
            @PathVariable Long id,
            @Valid @RequestBody ActualizarObsRequest request) {
        actualizarObservacionesService.actualizar(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/config/materia-prima")
    public ResponseEntity<Void> consumirMateriaPrima(
            @PathVariable Long id,
            @Valid @RequestBody IdPaletMateriaPrimaRequest request) {
        consumirMateriaPrimaService.consumir(id, request);
        return ResponseEntity.noContent().build();
    }
}