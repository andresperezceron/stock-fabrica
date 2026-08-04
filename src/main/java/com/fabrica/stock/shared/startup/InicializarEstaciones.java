package com.fabrica.stock.shared.startup;

import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.estacion.domain.Estaciones;
import com.fabrica.stock.estacion.infrastructure.EstacionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InicializarEstaciones implements CommandLineRunner {
    private final EstacionRepository estacionRepository;

    public InicializarEstaciones(EstacionRepository estacionRepository) {
        this.estacionRepository = estacionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Estaciones.todas().forEach(this::crearSiNoExiste);
    }

    private void crearSiNoExiste(String nombre) {
        if(!estacionRepository.existsByNombre(nombre)) {
            estacionRepository.save(new Estacion(nombre));
        }
    }
}