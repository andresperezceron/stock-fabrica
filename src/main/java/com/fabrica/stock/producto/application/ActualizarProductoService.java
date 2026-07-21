package com.fabrica.stock.producto.application;

import com.fabrica.stock.familia.application.ObtenerFamiliaService;
import com.fabrica.stock.familia.domain.Familia;
import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.validation.ActualizarProductoValidator;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ActualizarProductoService {
    private final ProductoRepository productoRepository;
    private final ObtenerProductoService obtenerProductoService;
    private final ObtenerFamiliaService obtenerFamiliaService;

    public ActualizarProductoService(ProductoRepository productoRepository,
                                     ObtenerProductoService obtenerProductoService,
                                     ObtenerFamiliaService obtenerFamiliaService)
    {
        this.productoRepository = productoRepository;
        this.obtenerProductoService = obtenerProductoService;
        this.obtenerFamiliaService = obtenerFamiliaService;
    }

    @Transactional
    public void actualizar(Long id, ProductoRequest request) {
        new ActualizarProductoValidator(request, productoRepository, id).validate();
        Producto anteriorProducto = obtenerProductoService.obtener(id);
        Familia nuevaFamilia = obtenerFamiliaService.obtener(request.familia_id);
        anteriorProducto.actualizar(
                request.codigo,
                request.descripcion,
                request.unidad,
                nuevaFamilia,
                request.piezasPorCaja,
                request.produccionPropia,
                request.admiteConfigProduccion);
    }
}