package com.fabrica.stock.maquina.domain;

import com.fabrica.stock.producto.domain.Producto;

public enum TipoMaquina {
    INYECCION {
        @Override
        public boolean puedeFabricar(Producto producto) {
            return producto.esDestinoInyeccion();
        }
    },

    EXTRUSION {
        @Override
        public boolean puedeFabricar(Producto producto) {
            return producto.esDestinoExtrusion();
        }
    },

    MOLINO {
        @Override
        public boolean puedeFabricar(Producto producto) {
            // El molino no fabrica productos registrados; transforma material recuperado.
            return true;
        }
    };

    public abstract boolean puedeFabricar(Producto producto);
}