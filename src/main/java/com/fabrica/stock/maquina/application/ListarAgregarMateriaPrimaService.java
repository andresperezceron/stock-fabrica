package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.maquina.domain.TipoMaquina;
import com.fabrica.stock.palet.domain.EstadoPalet;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.palet.infrastructure.PaletRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarAgregarMateriaPrimaService {
    private final ObtenerMaquinaService obtenerMaquinaService;
    private final PaletRepository paletRepository;

    public ListarAgregarMateriaPrimaService(ObtenerMaquinaService obtenerMaquinaService,
                                            PaletRepository paletRepository)
    {
        this.obtenerMaquinaService = obtenerMaquinaService;
        this.paletRepository = paletRepository;
    }

    public List<Palet> listar(Long maquinaId) {
        Maquina maquina = obtenerMaquinaService.obtener(maquinaId);
        TipoMaquina tipo = maquina.getTipo();

        return paletRepository
                .findAllByEstado(EstadoPalet.VALIDADO)
                .stream()
                .filter(Palet::soyMateriaPrima)
                .filter( p -> tipo.materiaPrimaPuedeUsar(p.getProducto()))
                .toList();
    }
}