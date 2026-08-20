package com.fabrica.stock.maquina.domain.vo;

import com.fabrica.stock.maquina.domain.constraints.MaquinaConstraints;
import com.fabrica.stock.maquina.domain.exceptions.NombreMaquinaNoValidoException;

public record NombreMaquina(String valor) {
    public NombreMaquina {
        if(!MaquinaConstraints.nombreValido(valor)) {
            throw new NombreMaquinaNoValidoException("Nombre de máquina no válido: " + valor);
        }
    }
}