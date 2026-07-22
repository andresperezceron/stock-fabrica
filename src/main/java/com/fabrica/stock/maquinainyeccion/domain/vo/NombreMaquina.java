package com.fabrica.stock.maquinainyeccion.domain.vo;

import com.fabrica.stock.maquinainyeccion.domain.constraints.MaquinaInyeccionConstraints;

import java.util.Objects;

public final class NombreMaquina {

    private final String valor;

    public NombreMaquina(String valor) {

        if (!MaquinaInyeccionConstraints.nombreValido(valor)) {
            throw new IllegalArgumentException(
                    "Nombre de máquina inválido: " + valor);
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
        if (this == o) return true;
        if (!(o instanceof NombreMaquina other)) return false;
        return Objects.equals(valor, other.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }
}