package com.fabrica.stock.maquinainyeccion.domain.constraints;

public final class MaquinaInyeccionConstraints {
    public static final int NOMBRE = 1;

    private MaquinaInyeccionConstraints() {}
    public static boolean nombreValido(String nombre) {
        return nombre != null
                && nombre.length() == NOMBRE
                && Character.isUpperCase(nombre.charAt(0));
    }
}