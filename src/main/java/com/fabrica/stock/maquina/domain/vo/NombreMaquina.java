package com.fabrica.stock.maquina.domain.vo;

import com.fabrica.stock.maquina.domain.constraints.MaquinaConstraints;
import com.fabrica.stock.maquina.domain.exceptions.NombreMaquinaNoValidoException;

import java.util.Objects;

public final class NombreMaquina {
    private final String valor;

    public NombreMaquina(String valor) {
        if(!MaquinaConstraints.nombreValido(valor)) {
            throw new NombreMaquinaNoValidoException("Nombre de máquina no válido: " + valor);
        }
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof NombreMaquina other)) return false;
        return Objects.equals(valor, other.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}