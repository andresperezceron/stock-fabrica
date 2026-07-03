package com.fabrica.stock.movimiento.application;

import com.fabrica.stock.lote.application.ObtenerLoteService;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.movimiento.domain.MovimientoStock;
import com.fabrica.stock.ubicacion.application.ObtenerUbicacionService;
import com.fabrica.stock.ubicacion.domain.UbicacionSistema;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class MoverStockService {
    private final ObtenerLoteService obtenerLoteService;
    private final ObtenerUbicacionService obtenerUbicacionService;
    private final CrearMovimientoService1 crearMovimientoService;

    public MoverStockService(
            ObtenerLoteService obtenerLoteService,
            ObtenerUbicacionService obtenerUbicacionService,
            CrearMovimientoService1 crearMovimientoService)
    {
        this.obtenerLoteService = obtenerLoteService;
        this.obtenerUbicacionService = obtenerUbicacionService;
        this.crearMovimientoService = crearMovimientoService;
    }

    public MovimientoStock mover(
            Lote lote,
            BigDecimal cantidad,
            UbicacionSistema origen,
            UbicacionSistema destino,
            LocalDateTime fecha)
    {
        /*return crearMovimientoService.crear(
                lote,
                TipoMovimiento.SALIDA,
                cantidad,
                fecha,

                ,
                destino
        );*/
        return null;
    }
}
