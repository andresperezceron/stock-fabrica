package com.fabrica.stock.produccion.application;

import com.fabrica.stock.lote.application.CrearLoteService;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.MaquinaProduccion;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.movimiento.api.dto.MovimientoRequest;
import com.fabrica.stock.movimiento.application.CrearMovimientoService;
import com.fabrica.stock.movimiento.domain.MovimientoStock;
import com.fabrica.stock.movimiento.domain.TipoMovimiento;
import com.fabrica.stock.produccion.api.dto.CerrarPaletRequest;
import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.ubicacion.application.ObtenerUbicacionService;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CerrarPaletService {
    private final CrearLoteService crearLoteService;
    private final ObtenerProductoService obtenerProductoService;
    private final ObtenerUbicacionService obtenerUbicacionService;
    private final CrearMovimientoService crearMovimientoService;

    public CerrarPaletService(
            CrearLoteService crearLoteService,
            ObtenerProductoService obtenerProductoService,
            ObtenerUbicacionService obtenerUbicacionService,
            CrearMovimientoService crearMovimientoService)
    {
        this.crearLoteService = crearLoteService;
        this.obtenerProductoService = obtenerProductoService;
        this.obtenerUbicacionService = obtenerUbicacionService;
        this.crearMovimientoService = crearMovimientoService;
    }

    public void cerrarPalet(CerrarPaletRequest request) {
        LocalDateTime fecha = LocalDateTime.now();
        OrigenLote origen = OrigenLote.PRODUCCION;
        MaquinaProduccion maquina = MaquinaProduccion.valueOf(request.maquina);
        Producto producto = obtenerProductoService.obtener(request.productoId);

        Lote lote = crearLoteService.crear(maquina, origen,null, producto, fecha);

        MovimientoRequest movimientoRequest = new MovimientoRequest();
        movimientoRequest.loteId = lote.getId();
        movimientoRequest.tipo = TipoMovimiento.ENTRADA.name();
        movimientoRequest.cantidad = request.cantidad;
        movimientoRequest.origenId = 1L;
        movimientoRequest.destinoId = 1L;
        MovimientoStock movimiento2 = crearMovimientoService.ejecutar(movimientoRequest);

        MovimientoStock movimiento = new MovimientoStock(
                lote,
                TipoMovimiento.ENTRADA,
                request.cantidad,
                fecha,
                new Ubicacion("PRODUCCION"),
                new Ubicacion("VALIDACION")
        );
    }
}
