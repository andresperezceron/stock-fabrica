package com.fabrica.stock.producto.application;

import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.vo.CodigoProducto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoEncontradoException;
import org.springframework.stereotype.Service;

@Service
public class ObtenerProductoService {
    private final ProductoRepository productoRepository;

    public ObtenerProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto obtener(Long id) {
        return productoRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe Producto con maquinaId: " + id));
    }

    public Producto obtener(CodigoProducto codigo) {
        return productoRepository.findByCodigoAndActivoTrue(codigo)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe Producto con codigo: " + codigo));
    }
}