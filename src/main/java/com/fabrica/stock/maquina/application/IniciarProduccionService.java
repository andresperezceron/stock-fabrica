package com.fabrica.stock.maquina.application;

import com.fabrica.stock.configproduccion.application.ObtenerConfigProduccionService;
import com.fabrica.stock.estacion.application.ObtenerEstacionService;
import com.fabrica.stock.maquina.api.dto.IniciarProduccionRequest;
import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import com.fabrica.stock.shared.domain.exceptions.EstadoNoValidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class IniciarProduccionService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final ObtenerEstacionService obtenerEstacionService;
    private final ObtenerConfigProduccionService obtenerConfigProduccionService;
    private final ComprobarIniProduccionService comprobarIniProduccionService;
    private final PaletRepository paletRepository;

    public IniciarProduccionService(ObtenerMaquinaService obtenerMaquinaService,
                                    ObtenerEstacionService obtenerEstacionService,
                                    ObtenerConfigProduccionService obtenerConfigProduccionService,
                                    ComprobarIniProduccionService comprobarIniProduccionService,
                                    PaletRepository paletRepository)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.obtenerEstacionService = obtenerEstacionService;
        this.obtenerConfigProduccionService = obtenerConfigProduccionService;
        this.comprobarIniProduccionService = comprobarIniProduccionService;
        this.paletRepository = paletRepository;
    }

    @Transactional
    public void iniciar(IniciarProduccionRequest request) {
        Maquina maquina = obtenerMaquinaService.obtener(request.maquinaId());

        comprobarIniProduccionService.comprobar(maquina);

        Palet palet = paletRepository.save(new Palet(
                request.tipoPalet(),
                maquina,
                maquina.getProductoActivo(),
                obtenerEstacionService.obtenerProduccion()));

        if(palet.soyTipoGranel())
            obtenerConfigProduccionService.porProducto(maquina.getProductoActivo()).actualizarCajasPorPalet(1);

        maquina.enProduccion();
    }
}