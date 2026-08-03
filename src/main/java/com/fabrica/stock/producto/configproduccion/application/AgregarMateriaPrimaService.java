package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.application.ObtenerPaletService;
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
    private final ObtenerPaletService obtenerPaletService;

    public AgregarMateriaPrimaService(ObtenerConfigProduccionService obtenerConfigProduccionService,
                                      ObtenerPaletService obtenerPaletService)
    {
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
        this.obtenerPaletService = obtenerPaletService;
    }

    @Transactional public void agregar(Maquina maquina, Producto materiaPrima) {
        ConfigProduccion config = obtenerConfigProduccionService.obtenerPorProducto(maquina.getProductoActivo());

        if(!materiaPrima.esMateriaPrima()) throw new MateriaPrimaNoValidaException("El producto no es materia prima.");
        else if(config.getMateriasPrimas()
                .stream()
                .anyMatch(mp -> mp.getId().equals(materiaPrima.getId()))
        ) throw new MateriaPrimaDupicadaException("La materia prima ya está agregada.");
        else if(maquina.estaProduccion() && !obtenerPaletService.obtenerPorMaquina(maquina).estaCerrado())
            throw new EstadoPaletInvalidoException( "No se puede modificar la configuración mientras " +
                    "exista un contenido abierto.");

        config.agregarMateriaPrima(materiaPrima);
        if(config.esValida())
            maquina.configurada();
        else maquina.enConfiguracion();
    }
}