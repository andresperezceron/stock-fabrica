package com.fabrica.stock.producto.application;

import com.fabrica.stock.familia.application.ObtenerFamiliaService;
import com.fabrica.stock.familia.domain.Familia;
import com.fabrica.stock.producto.api.dto.ProductoRequest;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.vo.CodigoProducto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ActualizarProductoService {
    private final ObtenerProductoService obtenerProductoService;
    private final ObtenerFamiliaService obtenerFamiliaService;
    private final ValidarCodigoProductoService validarCodigoProductoService;

    public ActualizarProductoService(ObtenerProductoService obtenerProductoService,
                                     ObtenerFamiliaService obtenerFamiliaService,
                                     ValidarCodigoProductoService validarCodigoProductoService)
    {
        this.obtenerProductoService = obtenerProductoService;
        this.obtenerFamiliaService = obtenerFamiliaService;
        this.validarCodigoProductoService = validarCodigoProductoService;
    }

    @Transactional
    public void actualizar(Long id, ProductoRequest request) {
        Producto producto = obtenerProductoService.obtener(id);
        Familia nuevaFamilia = obtenerFamiliaService.obtener(request.familia_id());
        CodigoProducto codigo = validarCodigoProductoService.validar(request.codigo(), producto);

        producto.actualizar(
                codigo,
                request.descripcion(),
                request.unidad(),
                nuevaFamilia,
                request.piezasPorCaja(),
                request.tipo(),
                request.origen(),
                request.destino());
    }
}