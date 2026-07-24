package com.fabrica.stock.maquina.api.dto;

import com.fabrica.stock.maquina.domain.TipoMaquina;
import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MaquinaRequest {
    @NotNull(message = "Tipo máquina es obligatorio.")
    public TipoMaquina tipo;

    @NotBlank(message = "Nombre máquina es obligatorio.")
    public String nombre;

    @NotNull(message = "Ubicación es obligatoria.")
    public Long ubicacionId;

    @Size(
            max = TextoConstraints.LARGO,
            message = "Observaciones demasiado larga. Max: " +  TextoConstraints.LARGO
    )
    public String observaciones;
}