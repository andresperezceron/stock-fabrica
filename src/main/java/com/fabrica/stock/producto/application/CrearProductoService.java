package com.fabrica.stock.producto.application;

import com.fabrica.stock.familia.application.ObtenerFamiliaService;
import com.fabrica.stock.familia.domain.Familia;
import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.vo.CodigoProducto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.shared.domain.exceptions.ConflictException;
import org.springframework.stereotype.Service;

@Service
public class CrearProductoService {
    private final ProductoRepository productoRepository;
    private final ObtenerFamiliaService obtenerFamiliaService;

    public CrearProductoService(ProductoRepository productoRepository,
                                ObtenerFamiliaService obtenerFamiliaService)
    {
        this.productoRepository = productoRepository;
        this.obtenerFamiliaService = obtenerFamiliaService;
    }

    public void crear(ProductoRequest request) {
        CodigoProducto codigoProducto = new CodigoProducto(request.codigo());

        if(productoRepository.existsByCodigo(codigoProducto))
            throw new ConflictException("Código duplicado.");

        Familia familia = obtenerFamiliaService.obtener(request.familia_id());
        String descNormalizada = request.descripcion().trim();

        productoRepository.save(new Producto(
                codigoProducto,
                descNormalizada,
                request.unidad(),
                familia,
                request.piezasPorCaja(),
                request.tipo(),
                request.origen(),
                request.destino()));
    }
}