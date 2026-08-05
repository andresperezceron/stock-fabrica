package com.fabrica.stock.movimiento.api.dto;

import com.fabrica.stock.movimiento.domain.Movimiento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovimientoResponse {
    public LocalDateTime fecha;
    public String codigoProducto;
    public String descripcionProducto;
    public BigDecimal cantidad;
    public String origen;
    public String destino;

    public MovimientoResponse(Movimiento movimiento) {
        fecha = movimiento.getFecha();
        codigoProducto = "";
        descripcionProducto = movimiento.getProducto().getDescripcion();
        cantidad = movimiento.getCantidad();
        //origen =  movimiento.getOrigen().getNombre();
        //destino = movimiento.getDestino().getNombre();
    }
}