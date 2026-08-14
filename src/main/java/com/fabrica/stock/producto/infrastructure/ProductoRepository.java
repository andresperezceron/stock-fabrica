package com.fabrica.stock.producto.infrastructure;

import com.fabrica.stock.producto.domain.DestinoProducto;
import com.fabrica.stock.producto.domain.OrigenProducto;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.domain.TipoProducto;
import com.fabrica.stock.producto.domain.vo.CodigoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
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
    List<Producto> findAllByTipoAndOrigenAndDestinoInOrderByDescripcionAsc(
            TipoProducto tipo,
            OrigenProducto origen,
            Collection<DestinoProducto> destinos);

    List<Producto> findAllByActivoTrueAndTipoOrderByDescripcionAsc(TipoProducto tipo);
}