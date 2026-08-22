package com.fabrica.stock.maquina.domain.vo;

import com.fabrica.stock.maquina.domain.constraints.MaquinaConstraints;
import com.fabrica.stock.shared.domain.exceptions.ConflictException;

public record NombreMaquina(String valor) {
    public NombreMaquina {
        if(!MaquinaConstraints.nombreValido(valor)) {
            throw new ConflictException("Nombre de máquina no válido: " + valor);
        }
    }
}