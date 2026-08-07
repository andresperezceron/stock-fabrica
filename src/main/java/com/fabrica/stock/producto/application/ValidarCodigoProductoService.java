package com.fabrica.stock.producto.application;

import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.exceptions.ProductoCodigoDuplicadoException;
import com.fabrica.stock.producto.domain.vo.CodigoProducto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidarCodigoProductoService {
    private final ProductoRepository productoRepository;

    public ValidarCodigoProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public CodigoProducto validar(String codigo) {
        CodigoProducto codigoProducto = new CodigoProducto(codigo);
        if(productoRepository.existsByCodigo(codigoProducto))
            throw new ProductoCodigoDuplicadoException("Código duplicado.");
        return codigoProducto;
    }

    public CodigoProducto validar(String codigo, Producto producto) {
        CodigoProducto codigoProducto = new CodigoProducto(codigo);
        if(productoRepository.existsByCodigoAndIdNot(codigoProducto, producto.getId()))
            throw new ProductoCodigoDuplicadoException("Código duplicado.");
        return codigoProducto;
    }
}
