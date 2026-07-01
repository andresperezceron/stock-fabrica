package com.fabrica.stock.lote.api.dto;

import com.fabrica.stock.lote.domain.MaquinaProduccion;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.lote.domain.constraints.LoteConstraints;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CrearLoteRequest {

    @NotBlank(message = "Codigo lote es obligatorio")
    @Size(max = LoteConstraints.CODIGO_LOTE)
    public String codigoLote;

    @NotNull(message = "Origen es obligatorio")
    public OrigenLote origenLote;

    @NotNull(message = "Maquina produccion es obligatoria.")
    public MaquinaProduccion maquinaProduccion;

    @Size(max = LoteConstraints.CODIGO_LOTE_PROVEEDOR)
    public String loteProveedor;

    @NotNull(message = "Identificador de producto no puede ser nulo.")
    public Long productoId;

    @NotNull(message = "Identificador de origen no puede ser nulo.")
    public Long origenId;

    @NotNull(message = "Identificador de destino no puede ser nulo.")
    public Long destinoId;
}