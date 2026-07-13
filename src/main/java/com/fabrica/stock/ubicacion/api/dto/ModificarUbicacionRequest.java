package com.fabrica.stock.ubicacion.api.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ModificarUbicacionRequest {
    @NotNull
    public Long ubicacionId;

    @NotBlank
    public String nombreModificado;
}
