package com.fabrica.stock.maquina.domain;

import com.fabrica.stock.producto.domain.Producto;

public enum TipoMaquina {
    INYECCION {
        @Override
        public boolean puedeFabricar(Producto producto) {
            boolean tipo = producto.soyTerminado() || producto.soyIntermedio();
            boolean origen = producto.esProduccionPropia();
            boolean destino = producto.esNinguno() || producto.esMontaje();
            return tipo && origen && destino;
        }

        @Override
        public boolean materiaPrimaPuedeUsar(Producto producto) {
            boolean tipo = producto.soyMateriaPrima();
            boolean origen = producto.esProduccionPropia() || producto.esOrigenCompra();
            boolean destino = producto.esInyeccion() || producto.esInyeccionOExtrusion();
            return tipo && origen && destino;
        }
    },

    EXTRUSION {
        @Override
        public boolean puedeFabricar(Producto producto) {
            boolean tipo = producto.soyTerminado();
            boolean origen = producto.esProduccionPropia();
            boolean destino = producto.esNinguno();
            return tipo && origen && destino;
        }

        @Override
        public boolean materiaPrimaPuedeUsar(Producto producto) {
            boolean tipo = producto.soyMateriaPrima();
            boolean origen = producto.esProduccionPropia() || producto.esOrigenCompra();
            boolean destino = producto.esExtrusion();
            return tipo && origen && destino;
        }
    },

    MOLINO {
        @Override
        public boolean puedeFabricar(Producto producto) {
            boolean tipo = producto.soyMateriaPrima();
            boolean origen = producto.esProduccionPropia();
            boolean destino = producto.esInyeccion() || producto.esExtrusion() || producto.esInyeccionOExtrusion();
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