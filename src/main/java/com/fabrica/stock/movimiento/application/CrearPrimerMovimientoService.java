package com.fabrica.stock.movimiento.application;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.movimiento.domain.MovimientoStock;
import com.fabrica.stock.movimiento.domain.TipoMovimiento;
import com.fabrica.stock.movimiento.infrastructure.MovimientoStockRepository;
import com.fabrica.stock.ubicacion.application.ObtenerUbicacionService;
import com.fabrica.stock.ubicacion.domain.UbicacionSistema;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class CrearPrimerMovimientoService {
    private final MovimientoStockRepository movimientoStockRepository;
    private final ObtenerUbicacionService obtenerUbicacionService;

    public CrearPrimerMovimientoService(MovimientoStockRepository movimientoStockRepository,
                                        ObtenerUbicacionService obtenerUbicacionService)
    {
        this.movimientoStockRepository = movimientoStockRepository;
        this.obtenerUbicacionService = obtenerUbicacionService;
    }

    public void crear(Lote lote, BigDecimal cantidad, LocalDateTime fecha) {
        MovimientoStock movimiento = new MovimientoStock(
                lote,
                TipoMovimiento.SALIDA,
                cantidad,
                fecha,
                obtenerUbicacionService.obtener(UbicacionSistema.PRODUCCION.name()),
                obtenerUbicacionService.obtener(UbicacionSistema.VALIDACION.name()));
        movimientoStockRepository.save(movimiento);
    }
}