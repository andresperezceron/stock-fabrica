package com.fabrica.stock.producto.application;

import com.fabrica.stock.familia.application.ObtenerFamiliaService;
import com.fabrica.stock.familia.domain.Familia;
import com.fabrica.stock.familia.infrastructure.FamiliaRepository;
import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.configproduccion.application.CrearConfigProduccionService;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.validation.CrearProductoValidator;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CrearProductoService {
    private final ProductoRepository productoRepository;
    private final FamiliaRepository familiaRepository;
    private final ObtenerFamiliaService obtenerFamiliaService;
    private final CrearConfigProduccionService crearConfigProduccionService;

    public CrearProductoService(ProductoRepository productoRepository,
                                FamiliaRepository familiaRepository,
                                ObtenerFamiliaService obtenerFamiliaService,
                                CrearConfigProduccionService crearConfigProduccionService)
    {
        this.productoRepository = productoRepository;
        this.familiaRepository = familiaRepository;
        this.obtenerFamiliaService = obtenerFamiliaService;
        this.crearConfigProduccionService = crearConfigProduccionService;
    }

    @Transactional
    public void crear(ProductoRequest request) {
        new CrearProductoValidator(request, productoRepository, familiaRepository).validate();
        Familia familia = obtenerFamiliaService.obtener(request.familia_id);
        Producto nuevoProducto = new Producto(
                request.codigo,
                request.descripcion,
                request.unidad,
                familia,
                request.piezasPorCaja,
                request.produccionPropia,
                request.admiteConfigProduccion);
        productoRepository.save(nuevoProducto);
        if(nuevoProducto.admiteConfigProduccion()) {
            crearConfigProduccionService.crear(nuevoProducto);
        }
    }
}