package com.fabrica.stock.maquina.api.dto;

import com.fabrica.stock.maquina.domain.TipoMaquina;
import com.fabrica.stock.maquina.domain.constraints.MaquinaConstraints;
import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MaquinaRequest(
        @NotNull(message = "Tipo máquina es obligatorio.")
        TipoMaquina tipo,

        @NotBlank(message = "Nombre máquina es obligatorio.")
        @Size(max = MaquinaConstraints.NOMBRE)
        String nombre
) {}