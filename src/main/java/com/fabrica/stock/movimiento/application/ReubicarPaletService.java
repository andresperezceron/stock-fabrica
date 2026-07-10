package com.fabrica.stock.movimiento.application;

import com.fabrica.stock.contenidopalet.domain.ContenidoPalet;
import com.fabrica.stock.movimiento.api.dto.MoverPaletRequest;
import com.fabrica.stock.movimiento.domain.MovimientoStock;
import com.fabrica.stock.movimiento.domain.TipoMovimiento;
import com.fabrica.stock.movimiento.infrastructure.MovimientoStockRepository;
import com.fabrica.stock.palet.application.ObtenerPaletService;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.ubicacion.application.ObtenerUbicacionService;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class ReubicarPaletService {
    private final MovimientoStockRepository movimientoStockRepository;
    private final ObtenerPaletService obtenerPaletService;
    private final ObtenerUbicacionService obtenerUbicacionService;

    public ReubicarPaletService(MovimientoStockRepository movimientoStockRepository,
                                ObtenerPaletService obtenerPaletService,
                                ObtenerUbicacionService obtenerUbicacionService)
    {
        this.movimientoStockRepository = movimientoStockRepository;
        this.obtenerPaletService = obtenerPaletService;
        this.obtenerUbicacionService = obtenerUbicacionService;
    }

    @Transactional
    public void moverPalet(MoverPaletRequest request) {
        Palet palet = obtenerPaletService.obtener(request.paletId);
        Ubicacion destino = obtenerUbicacionService.obtener(request.destinoId);
        Ubicacion origen = palet.getUbicacion();
        LocalDateTime fecha = LocalDateTime.now();

        for(ContenidoPalet contenido : palet.getContenidos()) {
            MovimientoStock movimiento = new MovimientoStock(
                    contenido.getProducto(),
                    contenido.getLote(),
                    TipoMovimiento.SALIDA,
                    contenido.getCantidad(),
                    fecha,
                    origen,
                    destino
            );
            movimientoStockRepository.save(movimiento);
        }
        palet.setUbicacion(destino);
    }
}