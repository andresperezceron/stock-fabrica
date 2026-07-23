package com.fabrica.stock.maquina.domain;

import com.fabrica.stock.maquina.domain.constraints.MaquinaConstraints;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import jakarta.persistence.*;

@Entity
@Table(name = "maquina_inyeccion")
public class Maquina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMaquina tipo;

    @Column(nullable = false, length = MaquinaConstraints.NOMBRE)
    private String nombre;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacion;

    @ManyToOne(optional = true)
    @JoinColumn(name = "producto_id")
    private Producto productoActivo;

    @Column(nullable = false, length = TextoConstraints.LARGO)
    private String observaciones;

    protected Maquina() {}

    public Maquina(TipoMaquina tipo, String nombre, Ubicacion ubicacion) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        observaciones = "";
    }

    public Long getId() { return id; }

    public TipoMaquina getTipo() { return tipo; }

    public String getNombre() { return nombre; }

    public Ubicacion getUbicacion() { return ubicacion; }

    public Producto getProductoActivo() { return productoActivo; }

    public String getObservaciones() { return observaciones; }

    public void asignarProductoActivo(Producto productoActivo) { this.productoActivo = productoActivo; }
}
