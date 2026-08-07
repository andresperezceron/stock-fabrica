package com.fabrica.stock.producto.application;

import com.fabrica.stock.lote.infrastructure.LoteRepository;
import com.fabrica.stock.maquina.application.ListarMaquinasService;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.exceptions.ProductoActivoEnMaquinaException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComprobarUsoProductoService {
    private final ListarMaquinasService listarMaquinasService;
    private final LoteRepository loteRepository;

    public ComprobarUsoProductoService(ListarMaquinasService listarMaquinasService,
                                       LoteRepository loteRepository)
    {
        this.listarMaquinasService = listarMaquinasService;
        this.loteRepository = loteRepository;
    }

    public boolean tieneUso(Producto producto) {
        List<Maquina> maquinas = listarMaquinasService.listar();
        for(Maquina maquina : maquinas)
            if(maquina.esMiProductoActivo(producto))
                throw new ProductoActivoEnMaquinaException("Producto activo: " + producto.getDescripcion() +
                        " en máquina: " + maquina.getNombre());
        return loteRepository.existsByProducto(producto);
    }
}