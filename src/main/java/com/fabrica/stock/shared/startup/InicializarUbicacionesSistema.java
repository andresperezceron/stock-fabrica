package com.fabrica.stock.shared.startup;

import com.fabrica.stock.ubicacion.domain.Ubicacion;
import com.fabrica.stock.ubicacion.domain.UbicacionSistema;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InicializarUbicacionesSistema implements CommandLineRunner {
    private final UbicacionRepository repository;

    public InicializarUbicacionesSistema(UbicacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        crearSiNoExiste(UbicacionSistema.PRODUCCION);
        crearSiNoExiste(UbicacionSistema.VALIDACION);
        crearSiNoExiste(UbicacionSistema.PROVEEDOR);
        crearSiNoExiste(UbicacionSistema.EXPEDICION);
    }

    private void crearSiNoExiste(UbicacionSistema ubicacionSistema) {
        if(!repository.existsByNombre(ubicacionSistema.name())) {
            repository.save(Ubicacion.sistema(ubicacionSistema.name()));
        }
    }
}