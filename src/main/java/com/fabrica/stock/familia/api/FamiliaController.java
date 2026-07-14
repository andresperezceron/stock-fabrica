package com.fabrica.stock.familia.api;

import com.fabrica.stock.familia.api.dto.CrearFamiliaRequest;
import com.fabrica.stock.familia.application.CrearFamiliaService;
import com.fabrica.stock.familia.application.ListarFamiliaService;
import com.fabrica.stock.familia.domain.Familia;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/familias")
public class FamiliaController {
    private final CrearFamiliaService crearFamiliaService;
    private final ListarFamiliaService listarFamiliaService;

    public FamiliaController(CrearFamiliaService crearFamiliaService,
                             ListarFamiliaService listarFamiliaService) {
        this.crearFamiliaService = crearFamiliaService;
        this.listarFamiliaService = listarFamiliaService;
    }

    @PostMapping
    public void crear(@Valid @RequestBody CrearFamiliaRequest request) {
        crearFamiliaService.crear(request);
    }

    @GetMapping
    public List<Familia> listar() {
        return listarFamiliaService.listar();
    }
}