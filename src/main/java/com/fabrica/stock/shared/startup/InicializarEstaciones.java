package com.fabrica.stock.shared.startup;

import com.fabrica.stock.estacion.application.CrearEstacionSiNoExisteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InicializarEstaciones implements CommandLineRunner {
    private final CrearEstacionSiNoExisteService crearEstacionSiNoExisteService;

    public InicializarEstaciones(CrearEstacionSiNoExisteService crearEstacionSiNoExisteService) {
        this.crearEstacionSiNoExisteService = crearEstacionSiNoExisteService;
    }

    @Override
    public void run(String... args) throws Exception {
        crearEstacionSiNoExisteService.crear("PRODUCCION");
        crearEstacionSiNoExisteService.crear("VALIDACION");
        crearEstacionSiNoExisteService.crear("PROVEEDOR");
        crearEstacionSiNoExisteService.crear("EXPEDICION");
        crearEstacionSiNoExisteService.crear("CORRECCION");
    }
}