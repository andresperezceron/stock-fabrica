package com.fabrica.stock.producto.application;

import com.fabrica.stock.familia.application.ObtenerFamiliaService;
import com.fabrica.stock.familia.domain.Familia;
import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.vo.CodigoProducto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.shared.domain.exceptions.ConflictException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ActualizarProductoService {
    private final ObtenerProductoService obtenerProductoService;
    private final ObtenerFamiliaService obtenerFamiliaService;
    private final ProductoRepository productoRepository;

    public ActualizarProductoService(ObtenerProductoService obtenerProductoService,
                                     ObtenerFamiliaService obtenerFamiliaService,
                                     ProductoRepository productoRepository)
    {
        this.obtenerProductoService = obtenerProductoService;
        this.obtenerFamiliaService = obtenerFamiliaService;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public void actualizar(Long id, ProductoRequest request) {
        Producto producto = obtenerProductoService.obtener(id);
        CodigoProducto codigoProducto = new CodigoProducto(request.codigo());

        if(productoRepository.existsByCodigoAndIdNot(codigoProducto, producto.getId()))
            throw new ConflictException("Código duplicado.");

        Familia nuevaFamilia = obtenerFamiliaService.obtener(request.familia_id());
        String descNormalizada = request.descripcion().trim();

        producto.actualizar(
                codigoProducto,
                descNormalizada,
                request.unidad(),
                nuevaFamilia,
                request.piezasPorCaja(),
                request.tipo(),
                request.origen(),
                request.destino()
        );
    }
}