package com.fabrica.stock.maquina.application;

import com.fabrica.stock.maquina.domain.Maquina;
import com.fabrica.stock.maquina.infrastructure.MaquinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarMaquinasService {
    private final MaquinaRepository maquinaRepository;

    public ListarMaquinasService(MaquinaRepository maquinaRepository) {
        this.maquinaRepository = maquinaRepository;
    }

    public List<Maquina> listar() { return this.maquinaRepository.findAllByOrderByNombreAsc(); }
}