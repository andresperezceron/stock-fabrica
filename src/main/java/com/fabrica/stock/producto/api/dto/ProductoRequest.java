package com.fabrica.stock.producto.api.dto;

import com.fabrica.stock.producto.domain.UnidadMedida;

public class ProductoRequest {
    public String codigo;
    public String descripcion;
    public UnidadMedida unidad;
    public String familia;
}
