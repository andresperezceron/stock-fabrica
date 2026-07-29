package com.fabrica.stock.shared.domain;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.domain.Palet;

public final class ProcesoProduccion {
    private final Maquina maquina;
    private final Palet palet;

    public ProcesoProduccion(Maquina maquina, Palet palet) {
        this.maquina = maquina;
        this.palet = palet;
    }
}