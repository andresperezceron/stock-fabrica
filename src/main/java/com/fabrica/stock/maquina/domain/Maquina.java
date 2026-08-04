package com.fabrica.stock.maquina.domain;

import com.fabrica.stock.estacion.domain.Estacion;
import com.fabrica.stock.lote.domain.TurnoProduccion;
import com.fabrica.stock.maquina.domain.constraints.MaquinaConstraints;
import com.fabrica.stock.maquina.domain.vo.NombreMaquina;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.shared.domain.EntidadActivable;
import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "maquina_inyeccion")
public class Maquina implements EntidadActivable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMaquina tipo;

    @Column(nullable = false, length = MaquinaConstraints.NOMBRE)
    private NombreMaquina nombre;

    @ManyToOne(optional = false)
    @JoinColumn(name = "estacion_id")
    private Estacion estacion;

    @ManyToOne(optional = true)
    @JoinColumn(name = "producto_id")
    private Producto productoActivo;

    @Column(nullable = false)
    private EstadoMaquina estado;

    @Column(nullable = false, length = TextoConstraints.LARGO)
    private String descripcion;

    @Column(nullable = false)
    private boolean activo = true;

    protected Maquina() {}

    public Maquina(TipoMaquina tipo, NombreMaquina nombre, Estacion estacion) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.estacion = estacion;
        descripcion = "";
        estado = EstadoMaquina.APTA;
    }

    public Long getId() { return id; }

    public TipoMaquina getTipo() { return tipo; }

    public NombreMaquina getNombre() { return nombre; }

    public Estacion getEstacion() { return estacion; }

    public Producto getProductoActivo() { return productoActivo; }

    public String getDescripcion() { return descripcion; }

    public EstadoMaquina getEstado() { return estado; }

    public void asignarProductoActivo(Producto productoActivo) { this.productoActivo = productoActivo; }

    public void quitarProductoActivo() { this.productoActivo = null; }

    public boolean esMiProductoActivo(Producto producto) {
        return this.productoActivo.mismaEntidadQue(producto);
    }

    public boolean tieneProductoActivo() { return productoActivo != null; }

    public boolean estaCambioMolde() { return estado == EstadoMaquina.CAMBIO_MOLDE; }

    public boolean estaFueraServicio() { return estado == EstadoMaquina.FUERA_SERVICIO; }

    public boolean estaMantenimiento() { return estado == EstadoMaquina.MANTENIMIENTO; }

    public boolean estaAptaProduccion() { return estado == EstadoMaquina.APTA; }

    public boolean estaConfigurada() { return estado == EstadoMaquina.CONFIGURADA; }

    public boolean estaProduccion() { return estado == EstadoMaquina.EN_PRODUCCION; }

    public boolean estaConfiguracion() { return estado == EstadoMaquina.EN_CONFIGURACION; }

    public void enProduccion() { estado = EstadoMaquina.EN_PRODUCCION; }

    public void configurada() { estado = EstadoMaquina.CONFIGURADA; }

    public void enConfiguracion() { estado = EstadoMaquina.EN_CONFIGURACION; }

    public void cambiarACambioMolde() {
        this.estado = EstadoMaquina.CAMBIO_MOLDE;
    }

    public void cambiarAFueraServicio() {
        this.estado = EstadoMaquina.FUERA_SERVICIO;
    }

    public void cambiarAMantenimiento() { this.estado = EstadoMaquina.MANTENIMIENTO; }

    public void cambiarAAptaProduccion() { this.estado = EstadoMaquina.APTA; }

    public void cambiarAConfigurada() { this.estado = EstadoMaquina.CONFIGURADA; }

    public void cambiarAEnProduccion() { this.estado = EstadoMaquina.EN_PRODUCCION; }

    public LoteGenerado obtenerLoteGenerado() { return new LoteGenerado(this.nombre); }

    @Override
    public boolean estaActiva() { return activo; }

    @Override
    public void activar() { activo = true; }

    @Override
    public void desactivar() { activo = false; }

    public static final class LoteGenerado {
        private final LocalDateTime fechaCreacion;
        private final TurnoProduccion turnoProduccion;
        private final String codigo;

        private LoteGenerado(NombreMaquina nombreMaquina) {
            fechaCreacion = LocalDateTime.now();
            turnoProduccion = TurnoProduccion.obtener(fechaCreacion);

            String primerDigitoYear = String.valueOf(fechaCreacion.getYear()).substring(0,1);
            String ultimoDigitoYear = String.valueOf(fechaCreacion.getYear()).substring(3);
            String dia = String.format("%03d", fechaCreacion.getDayOfYear());
            codigo =  nombreMaquina.valor() + "-"
                    + primerDigitoYear + dia + ultimoDigitoYear + "-"
                    + turnoProduccion.getCodigo();
        }

        public String getCodigo() { return codigo; }

        public LocalDateTime getFechaCreacion() { return fechaCreacion; }

        public TurnoProduccion getTurnoProduccion() { return turnoProduccion; }
    }
}