package com.fabrica.stock.palet.application;

import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.exceptions.EstadoPaletInvalidoException;
import com.fabrica.stock.producto.application.ConfigProduccionService;
import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AbrirPaletService {
    private final ObtenerPaletService obtenerPaletService;
    private final ConfigProduccionService configProduccionService;

    public AbrirPaletService(ObtenerPaletService obtenerPaletService,
                             ConfigProduccionService configProduccionService) {
        this.obtenerPaletService = obtenerPaletService;
        this.configProduccionService = configProduccionService;
    }

    @Transactional
    public void abrir(Long id) {
        Palet palet = obtenerPaletService.obtener(id);
        if(!palet.estaCerrado()) throw new EstadoPaletInvalidoException("El palet no está cerrado.");
        if(configProduccionService.obtenerConfigPorProducto(palet.getMaquina().getProductoActivo()).esValida())
            throw new EstadoPaletInvalidoException("La configuracion actual de produccion no es valida.") ;
        palet.abrir();

        /* if(!palet.estaCerrado()) throw new EstadoPaletInvalidoException("El palet no está cerrado.");
        else if(palet.procesoEstaPausado()) throw new EstadoPaletInvalidoException("La configuracion actual de produccion no es valida.") ;
        palet.abrir(); */
    }
}