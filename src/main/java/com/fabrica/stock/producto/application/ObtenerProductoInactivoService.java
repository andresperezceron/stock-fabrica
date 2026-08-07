package com.fabrica.stock.producto.application;

import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.exceptions.ProductoNoEncontradoException;
import com.fabrica.stock.producto.domain.vo.CodigoProducto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class ObtenerProductoInactivoService {
    private final ProductoRepository productoRepository;

    public ObtenerProductoInactivoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto obtener(Long id) {
        return productoRepository.findByIdAndActivoFalse(id).orElseThrow(() ->
                new ProductoNoEncontradoException("No existe Producto Inactivo con maquinaId: " + id));
    }

    public Producto obtener(CodigoProducto codigo) {
        return productoRepository.findByCodigoAndActivoFalse(codigo).orElseThrow(() ->
                new ProductoNoEncontradoException("No existe Producto Inactvio con codigo: " + codigo));
    }
}