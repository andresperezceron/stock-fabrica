package com.fabrica.stock.maquina.domain;

import com.fabrica.stock.producto.domain.Producto;

public enum TipoMaquina {
    INYECCION {
        @Override
        public boolean puedeFabricar(Producto producto) {
            boolean tipo = producto.soyTipoTerminado() || producto.soyTipoIntermedio();
            boolean origen = producto.esOrigenProduccionPropia();
            boolean destino = producto.esDestinoNinguno() || producto.esDestinoMontaje();
            return tipo && origen && destino;
        }

        @Override
        public boolean materiaPrimaPuedeUsar(Producto producto) {
            boolean tipo = producto.soyTipoMateriaPrima();
            boolean origen = producto.esOrigenProduccionPropia() || producto.esOrigenCompra();
            boolean destino = producto.esDestinoInyeccion() || producto.esDestinoInyExt();
            return tipo && origen && destino;
        }
    },

    EXTRUSION {
        @Override
        public boolean puedeFabricar(Producto producto) {
            boolean tipo = producto.soyTipoTerminado();
            boolean origen = producto.esOrigenProduccionPropia();
            boolean destino = producto.esDestinoNinguno();
            return tipo && origen && destino;
        }

        @Override
        public boolean materiaPrimaPuedeUsar(Producto producto) {
            boolean tipo = producto.soyTipoMateriaPrima();
            boolean origen = producto.esOrigenProduccionPropia() || producto.esOrigenCompra();
            boolean destino = producto.esDestinoExtrusion();
            return tipo && origen && destino;
        }
    },

    MOLINO {
        @Override
        public boolean puedeFabricar(Producto producto) {
            boolean tipo = producto.soyTipoMateriaPrima();
            boolean origen = producto.esOrigenProduccionPropia();
            boolean destino = producto.esDestinoInyeccion() || producto.esDestinoExtrusion() || producto.esDestinoInyExt();
            return tipo && origen && destino;
        }

        @Override
        public boolean materiaPrimaPuedeUsar(Producto producto) {
            return true;
        }
    };

    public abstract boolean puedeFabricar(Producto producto);
    public abstract boolean materiaPrimaPuedeUsar(Producto producto);
}