package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.application.ExisteContenidoAbiertoService;
import com.fabrica.stock.palet.domain.exceptions.EstadoPaletInvalidoException;
import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class QuitarMateriaPrimaService {
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;
    private final ExisteContenidoAbiertoService existeContenidoAbiertoService;

    public QuitarMateriaPrimaService(ObtenerConfigProduccionService obtenerConfigProduccionService,
                                     ExisteContenidoAbiertoService existeContenidoAbiertoService)
    {
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
        this.existeContenidoAbiertoService = existeContenidoAbiertoService;
    }

    @Transactional
    public void quitar(Maquina maquina, Producto materiaPrima) {
        ConfigProduccion config = obtenerConfigProduccionService.obtenerPorProducto(maquina.getProductoActivo());

        if(maquina.estaProduccion() && existeContenidoAbiertoService.existe(maquina))
            throw new EstadoPaletInvalidoException( "No se puede modificar la configuración mientras " +
                    "exista un contenido abierto."); config.quitarMateriaPrima(materiaPrima);

        if(config.esValida()) maquina.configurada();
        else maquina.enConfiguracion();
    }
}