package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.application.ExisteContenidoAbiertoService;
import com.fabrica.stock.palet.domain.exceptions.EstadoPaletInvalidoException;
import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.MateriaPrimaDupicadaException;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.MateriaPrimaNoValidaException;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AgregarMateriaPrimaService {
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;
    private final ExisteContenidoAbiertoService existeContenidoAbiertoService;

    public AgregarMateriaPrimaService(ObtenerConfigProduccionService obtenerConfigProduccionService,
                                      ExisteContenidoAbiertoService existeContenidoAbiertoService)
    {
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
        this.existeContenidoAbiertoService = existeContenidoAbiertoService;
    }

    @Transactional
    public void agregar(Maquina maquina, Producto materiaPrima) {
        ConfigProduccion config = obtenerConfigProduccionService.obtenerPorProducto(maquina.getProductoActivo());

        if(!materiaPrima.esMateriaPrima()) throw new MateriaPrimaNoValidaException("El producto no es materia prima.");
        else if(config.getMateriasPrimas()
                .stream()
                .anyMatch(mp -> mp.getId().equals(materiaPrima.getId()))
        ) throw new MateriaPrimaDupicadaException("La materia prima ya está agregada.");
        else if(maquina.estaProduccion() && existeContenidoAbiertoService.existe(maquina))
            throw new EstadoPaletInvalidoException( "No se puede modificar la configuración mientras " +
                    "exista un contenido abierto.");

        config.agregarMateriaPrima(materiaPrima);
        if(config.esValida())
            maquina.configurada();
        else maquina.enConfiguracion();
    }
}