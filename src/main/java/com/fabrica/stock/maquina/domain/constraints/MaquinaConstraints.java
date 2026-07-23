package com.fabrica.stock.maquina.domain.constraints;

public final class MaquinaConstraints {
    public static final int NOMBRE = 1;

    private MaquinaConstraints() {}
    public static boolean nombreValido(String nombre) {
        return nombre != null
                && nombre.length() == NOMBRE
                && Character.isUpperCase(nombre.charAt(0));
    }
}