package com.fabrica.stock.producto.application;

import com.fabrica.stock.familia.application.ObtenerFamiliaService;
import com.fabrica.stock.familia.domain.Familia;
import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.vo.CodigoProducto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CrearProductoService {
    private final ProductoRepository productoRepository;
    private final ObtenerFamiliaService obtenerFamiliaService;
    private final ValidarCodigoProductoService validarCodigoProductoService;

    public CrearProductoService(ProductoRepository productoRepository,
                                ObtenerFamiliaService obtenerFamiliaService,
                                ValidarCodigoProductoService validarCodigoProductoService)
    {
        this.productoRepository = productoRepository;
        this.obtenerFamiliaService = obtenerFamiliaService;
        this.validarCodigoProductoService = validarCodigoProductoService;
    }

    @Transactional
    public void crear(ProductoRequest request) {
        Familia familia = obtenerFamiliaService.obtener(request.familia_id());
        CodigoProducto codigo = validarCodigoProductoService.validar(request.codigo());
        Producto nuevoProducto = new Producto(
                codigo,
                request.descripcion(),
                request.unidad(),
                familia,
                request.piezasPorCaja(),
                request.tipo(),
                request.origen(),
                request.destino());
        productoRepository.save(nuevoProducto);
    }
}