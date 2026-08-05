package com.fabrica.stock.producto.configproduccion.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.domain.EstadoProcesoPalet;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.domain.exceptions.EstadoPaletInvalidoException;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import com.fabrica.stock.producto.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.MateriaPrimaDupicadaException;
import com.fabrica.stock.producto.configproduccion.domain.exceptions.MateriaPrimaNoValidaException;
import com.fabrica.stock.producto.domain.Producto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class AgregarMateriaPrimaService {
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;
    private final PaletRepository paletRepository;

    public AgregarMateriaPrimaService(ObtenerConfigProduccionService obtenerConfigProduccionService,
                                      PaletRepository paletRepository)
    {
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
        this.paletRepository = paletRepository;
    }

    @Transactional
    public void agregar(Maquina maquina, Producto materiaPrima) {
        ConfigProduccion config = obtenerConfigProduccionService.obtenerPorProducto(maquina.getProductoActivo());

        if(!materiaPrima.esMateriaPrima()) throw new MateriaPrimaNoValidaException("El producto no es materia prima.");
        else if(config.getMateriasPrimas()
                .stream()
                .anyMatch(mp -> mp.getId().equals(materiaPrima.getId()))
        ) throw new MateriaPrimaDupicadaException("La materia prima ya está agregada.");
        else if(maquina.estaProduccion()) {
            Palet palet = paletRepository
                    .findByMaquinaAndProceso(maquina, EstadoProcesoPalet.PAUSADO)
                    .orElseThrow(() -> new EstadoPaletInvalidoException("El palet no esta cerrado."));
            config.agregarMateriaPrima(materiaPrima);
            if(config.esValida()) palet.iniciarProceso();
        }else config.agregarMateriaPrima(materiaPrima);
    }
}