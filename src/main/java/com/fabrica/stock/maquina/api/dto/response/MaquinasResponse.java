package com.fabrica.stock.maquina.api.dto.response;

import com.fabrica.stock.maquina.domain.Maquina;

import java.util.List;

public record MaquinasResponse(List<MaquinaResponse> maquinas) {
    public static MaquinasResponse of(List<Maquina> maquinas) {
        return new MaquinasResponse(maquinas.stream().map(MaquinaResponse::of).toList());
    }
}