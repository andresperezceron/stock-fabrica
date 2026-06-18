package com.fabrica.stock;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.lote.infrastructure.LoteRepository;
import com.fabrica.stock.movimiento.domain.MovimientoStock;
import com.fabrica.stock.movimiento.domain.TipoMovimiento;
import com.fabrica.stock.movimiento.infrastructure.MovimientoStockRepository;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.stock.application.StockService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
public class StockFabricaApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockFabricaApplication.class, args);
    }

   /* @Bean
    CommandLineRunner initData(
            ProductoRepository productoRepo,
            LoteRepository loteRepo,
            MovimientoStockRepository movimientoRepo,
            StockService stockService
    ) {
        return args -> {

            // 1. Producto
            Producto producto = new Producto(
                    "PVC-20",
                    "Tubería PVC 20mm",
                    "UNIDADES",
                    "TUBERIAS"
            );

            producto = productoRepo.save(producto);

            // 2. Lote
            Lote lote = new Lote(
                    "LOTE-001",
                    OrigenLote.PRODUCCION,
                    null,
                    producto,
                    LocalDateTime.now(),
                    new BigDecimal("100")
            );

            lote = loteRepo.save(lote);

            // 3. Movimientos
            movimientoRepo.save(new MovimientoStock(
                    lote,
                    TipoMovimiento.ENTRADA,
                    new BigDecimal("100"),
                    LocalDateTime.now()
            ));

            movimientoRepo.save(new MovimientoStock(
                    lote,
                    TipoMovimiento.SALIDA,
                    new BigDecimal("30"),
                    LocalDateTime.now()
            ));

            movimientoRepo.save(new MovimientoStock(
                    lote,
                    TipoMovimiento.ENTRADA,
                    new BigDecimal("20"),
                    LocalDateTime.now()
            ));

            // 4. Consulta stock
            System.out.println(
                    "STOCK FINAL LOTE 1: " +
                            stockService.calcularStockPorLote(lote.getId())
            );
        };

    }*/
}
