package com.fabrica.stock.lote.api.dto;

import com.fabrica.stock.lote.domain.MaquinaProduccion;
import com.fabrica.stock.lote.domain.OrigenLote;

public class CrearLote {
    public OrigenLote origenLote;
    public String loteProveedor;
    public Long productoId;
    public MaquinaProduccion maquinaProduccion;
}