package com.fabrica.stock.produccion.application;

import com.fabrica.stock.lote.application.CrearLoteService;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.MaquinaProduccion;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.movimiento.domain.MovimientoStock;
import com.fabrica.stock.movimiento.domain.TipoMovimiento;
import com.fabrica.stock.movimiento.infrastructure.MovimientoStockRepository;
import com.fabrica.stock.produccion.api.dto.CerrarPaletRequest;
import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.ubicacion.application.ObtenerUbicacionService;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.UbicacionSistema;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CerrarPaletService {
    private final CrearLoteService crearLoteService;
    private final ObtenerProductoService obtenerProductoService;
    private final MovimientoStockRepository movimientoStockRepository;
    private final ObtenerUbicacionService obtenerUbicacionService;

    public CerrarPaletService(
            CrearLoteService crearLoteService,
            ObtenerProductoService obtenerProductoService,
            MovimientoStockRepository movimientoStockRepository,
            ObtenerUbicacionService obtenerUbicacionService)
    {
        this.crearLoteService = crearLoteService;
        this.obtenerProductoService = obtenerProductoService;
        this.movimientoStockRepository = movimientoStockRepository;
        this.obtenerUbicacionService = obtenerUbicacionService;
    }

    public MovimientoStock cerrarPalet(CerrarPaletRequest request) {
        LocalDateTime fecha = LocalDateTime.now();
        OrigenLote origen = OrigenLote.PRODUCCION;
        MaquinaProduccion maquina = MaquinaProduccion.valueOf(request.maquina);
        Producto producto = obtenerProductoService.obtener(request.productoId);
        Ubicacion ubicacionOrigen = obtenerUbicacionService.obtener(UbicacionSistema.PRODUCCION.name());
        Ubicacion ubicacionDestino = obtenerUbicacionService.obtener(UbicacionSistema.VALIDACION.name());

        Lote lote = crearLoteService.crear(producto, origen,null, maquina, fecha);
        return movimientoStockRepository.save(new MovimientoStock(
                lote,
                TipoMovimiento.ENTRADA,
                request.cantidad,
                fecha,
                ubicacionOrigen,
                ubicacionDestino
        ));



    }
}
