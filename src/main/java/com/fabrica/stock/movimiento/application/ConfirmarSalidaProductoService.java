package com.fabrica.stock.movimiento.application;

import com.fabrica.stock.movimiento.api.dto.ConfirmarSalidaRequest;
import com.fabrica.stock.movimiento.api.dto.ConfirmarSalidaResponse;
import com.fabrica.stock.movimiento.api.dto.LoteConfirmarSalidaRequest;
import com.fabrica.stock.movimiento.api.dto.MovimientoRequest;
import com.fabrica.stock.movimiento.domain.TipoMovimiento;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ConfirmarSalidaProductoService {
    private final CrearMovimientoService crearMovimientoService;

    public ConfirmarSalidaProductoService(CrearMovimientoService crearMovimientoService) {
        this.crearMovimientoService = crearMovimientoService;
    }

    @Transactional
    public ConfirmarSalidaResponse salida(ConfirmarSalidaRequest confirmarSalidaRequest) {
        for(LoteConfirmarSalidaRequest lote : confirmarSalidaRequest.lotes) {
            MovimientoRequest movimientoRequest = new MovimientoRequest();
            movimientoRequest.loteId = lote.idLote;
            movimientoRequest.tipo = String.valueOf(TipoMovimiento.SALIDA);
            movimientoRequest.cantidad = lote.cantidad;
            crearMovimientoService.ejecutar(movimientoRequest);
        }

        ConfirmarSalidaResponse response = new ConfirmarSalidaResponse();
        response.mngSalida = "Operación de salida realizada con exito";
        return response;
    }
}