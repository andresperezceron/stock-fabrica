package com.fabrica.stock.producto.application;

import com.fabrica.stock.contenidopalet.infrastructure.ContenidoPaletRepository;
import com.fabrica.stock.maquina.application.ListarMaquinasService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.maquina.domain.exceptions.ProductoActivoNoValidoException;
import com.fabrica.stock.movimiento.infrastructure.MovimientoStockRepository;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.exceptions.ProductoActivoEnMaquinaException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComprobarUsoProductoService {
    private final MovimientoStockRepository movimientoStockRepository;
    private final ContenidoPaletRepository contenidoPaletRepository;
    private final ListarMaquinasService listarMaquinasService;

    public ComprobarUsoProductoService(MovimientoStockRepository movimientoStockRepository,
                                       ContenidoPaletRepository contenidoPaletRepository,
                                       ListarMaquinasService listarMaquinasService) {
        this.movimientoStockRepository = movimientoStockRepository;
        this.contenidoPaletRepository = contenidoPaletRepository;
        this.listarMaquinasService = listarMaquinasService;
    }

    public boolean tieneUso(Producto producto) {
        List<Maquina> maquinas = listarMaquinasService.listar();
        for(Maquina maquina : maquinas)
            if(maquina.esMiProductoActivo(producto))
                throw new ProductoActivoEnMaquinaException("Producto activo: " + producto.getDescripcion() +
                        " en máquina: " + maquina.getNombre());

        return movimientoStockRepository.existsByProducto(producto); //||
                //contenidoPaletRepository.existsByProducto(producto);
    }
}