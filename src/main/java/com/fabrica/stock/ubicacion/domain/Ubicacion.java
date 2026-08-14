package com.fabrica.stock.ubicacion.domain;

import com.fabrica.stock.shared.domain.EntidadActivable;
import com.fabrica.stock.shared.domain.Localizacion;
import com.fabrica.stock.ubicacion.domain.vo.NombreUbicacion;
import jakarta.persistence.*;

@Entity
public class Ubicacion extends Localizacion implements EntidadActivable {
    @Column(nullable = false)
    private boolean activo = true;

    protected Ubicacion() {}

    public Ubicacion(NombreUbicacion nombre) {
        super(nombre);
    }

    public Long getId() { return super.getId(); }

    public NombreUbicacion getNombre() { return super.getNombre(); }

    public void actualizarNombre(NombreUbicacion nombre) { super.setNombre(nombre); }

    @Override
    public boolean estaActiva() {
        return activo;
    }

    @Override
    public void activar() {
        activo = true;
    }

    @Override
    public void desactivar() {
        activo = false;
    }
}