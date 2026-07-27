package com.fabrica.stock.shared.domain;

public interface EntidadActivable {
    boolean estaActiva();
    void activar();
    void desactivar();
}