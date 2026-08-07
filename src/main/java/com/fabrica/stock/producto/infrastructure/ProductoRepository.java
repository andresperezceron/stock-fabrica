package com.fabrica.stock.producto.infrastructure;

import com.fabrica.stock.producto.api.dto.ProductoInyecExtruResponse;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.vo.CodigoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    boolean existsByCodigo(CodigoProducto codigo);
    boolean existsByCodigoAndIdNot(CodigoProducto codigo, Long id);
    Optional<Producto> findByIdAndActivoTrue(Long id);
    Optional<Producto> findByIdAndActivoFalse(Long id);
    Optional<Producto> findByCodigoAndActivoTrue(CodigoProducto codigo);
    Optional<Producto> findByCodigoAndActivoFalse(CodigoProducto codigo);
    List<Producto> findAllByActivoTrueOrderByDescripcionAsc();
    List<Producto> findAllByActivoFalseOrderByDescripcionDesc();

    @Query("""
    SELECT new com.fabrica.stock.producto.api.dto.ProductoInyecExtruResponse(
        p.id,
        p.descripcion
    )
    FROM Producto p
    WHERE p.activo = true
      AND p.tipo IN (
            com.fabrica.stock.producto.domain.TipoProducto.TERMINADO,
            com.fabrica.stock.producto.domain.TipoProducto.INTERMEDIO
      )
      AND p.origen = com.fabrica.stock.producto.domain.OrigenProducto.PRODUCCION_PROPIA
      AND p.destino IN (
            com.fabrica.stock.producto.domain.DestinoProducto.NINGUNO,
            com.fabrica.stock.producto.domain.DestinoProducto.MONTAJE
      )
    ORDER BY p.descripcion
    """)
    List<ProductoInyecExtruResponse> obtenerProductosInyeccionYExtrusion();
}