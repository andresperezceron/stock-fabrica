package com.fabrica.stock.palet.application;

import com.fabrica.stock.maquina.domain.exceptions.EstadoMaquinaNoValidoException;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.exceptions.EstadoPaletInvalidoException;
import com.fabrica.stock.producto.application.ConfigProduccionService;
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
        if(!palet.getMaquina().estaProduccion()) throw new EstadoMaquinaNoValidoException("No hay producción activa.");
        else if(!palet.estaCerrado()) throw new EstadoPaletInvalidoException("El palet no está cerrado.");
        else if(!configProduccionService.validarConfigPorProducto(palet.getMaquina().getProductoActivo()))
            throw new EstadoPaletInvalidoException("La configuracion actual de produccion no es valida.") ;
        palet.abrir();
    }
}