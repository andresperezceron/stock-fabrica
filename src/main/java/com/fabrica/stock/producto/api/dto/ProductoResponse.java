package com.fabrica.stock.producto.api.dto;

import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.UnidadMedida;

public class ProductoResponse {
    private final Long id;
    private final String codigo;
    private final String descripcion;
    private final UnidadMedida unidad;
    private final Long familiaId;
    private final int piezasXcaja;

    private ProductoResponse(Producto producto) {
        id = producto.getId();
        codigo = producto.getCodigo();
        descripcion = producto.getDescripcion();
        unidad = producto.getUnidad();
        familiaId = producto.getFamilia().getId();
        piezasXcaja = producto.getPiezasPorCaja();
    }

    public static ProductoResponse of(Producto producto) {
        return new ProductoResponse(producto);
    }

    public Long getId() { return id; }

    public String getCodigo() { return codigo; }

    public String getDescripcion() { return descripcion; }

    public UnidadMedida getUnidad() { return unidad; }

    public Long getFamiliaId() { return familiaId; }

    public int getPiezasXcaja() { return piezasXcaja; }
}