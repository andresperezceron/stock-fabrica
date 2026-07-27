package com.fabrica.stock.maquina.domain;

import com.fabrica.stock.maquina.domain.constraints.MaquinaConstraints;
import com.fabrica.stock.maquina.domain.vo.NombreMaquina;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.shared.domain.EntidadActivable;
import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import com.fabrica.stock.ubicacion.domain.Ubicacion;
import jakarta.persistence.*;

@Entity
@Table(name = "maquina_inyeccion")
public class Maquina implements EntidadActivable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMaquina tipo;

    @Column(nullable = false, length = MaquinaConstraints.NOMBRE)
    private NombreMaquina nombre;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacion;

    @ManyToOne(optional = true)
    @JoinColumn(name = "producto_id")
    private Producto productoActivo;

    @Column(nullable = false, length = TextoConstraints.LARGO)
    private String descripcion;

    @Column(nullable = false)
    private EstadoMaquina estado;

    @Column(nullable = false)
    private boolean activo = true;

    protected Maquina() {}

    public Maquina(TipoMaquina tipo, NombreMaquina nombre, Ubicacion ubicacion) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        descripcion = "";
    }

    public Long getId() { return id; }

    public TipoMaquina getTipo() { return tipo; }

    public NombreMaquina getNombre() { return nombre; }

    public Ubicacion getUbicacion() { return ubicacion; }

    public Producto getProductoActivo() { return productoActivo; }

    public String getDescripcion() { return descripcion; }

    public void asignarProductoActivo(Producto productoActivo) { this.productoActivo = productoActivo; }

    public boolean esMiProductoActivo(Producto producto) {
        return this.productoActivo.mismaEntidadQue(producto);
    }

    public boolean tieneProductoActivo() {
        return productoActivo != null;
    }

    public boolean estaFueraDeServicio() {
        return estado == EstadoMaquina.FUERA_SERVICIO;
    }

    public boolean estaCambioMolde() {
        return estado == EstadoMaquina.CAMBIO_MOLDE;
    }

    public void asignarEstadoCambioMolde() {
        this.estado = EstadoMaquina.CAMBIO_MOLDE;
    }

    public void asignarEstadoFueraServicio() {
        this.estado = EstadoMaquina.FUERA_SERVICIO;
    }

    @Override
    public boolean estaActiva() { return activo; }

    @Override
    public void activar() { activo = true; }

    @Override
    public void desactivar() { activo = false; }
}