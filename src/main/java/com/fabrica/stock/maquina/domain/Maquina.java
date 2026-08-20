package com.fabrica.stock.maquina.domain;

import com.fabrica.stock.configproduccion.domain.ConfigProduccion;
import com.fabrica.stock.lote.domain.TurnoProduccion;
import com.fabrica.stock.maquina.domain.constraints.MaquinaConstraints;
import com.fabrica.stock.maquina.domain.vo.NombreMaquina;
import com.fabrica.stock.palet.domain.Palet;
import com.fabrica.stock.producto.domain.Producto;
import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.function.Predicate;

@Entity
@Table(name = "maquina_inyeccion")
public class Maquina {
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

    @ManyToOne(optional = true)
    @JoinColumn(name = "config_produccion_id")
    private ConfigProduccion configProduccion;

    @ManyToOne(optional = true)
    @JoinColumn(name = "palet_id")
    private Palet palet;

    @Column(nullable = false)
    private EstadoMaquina estado;

    @Column(nullable = false, length = TextoConstraints.LARGO)
    private String descripcion;

    private LocalDateTime fechaCambioEstado;

    protected Maquina() {}

    public Maquina(TipoMaquina tipo, NombreMaquina nombre, String descripcion) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        configProduccion = null;
        palet = null;
        estado = EstadoMaquina.APTA_PRODUCCION;
        fechaCambioEstado = LocalDateTime.now();
    }

    public Long getId() { return id; }

    public TipoMaquina getTipo() { return tipo; }

    public NombreMaquina getNombre() { return nombre; }

    public ConfigProduccion getConfigProduccion() { return configProduccion; }

    public String getDescripcion() { return descripcion; }

    public EstadoMaquina getEstado() { return estado; }

    public Palet getPalet() { return palet; }

    public LocalDateTime getFechaCambioEstado() { return fechaCambioEstado; }

    public boolean puedeFabricar(Producto producto) { return tipo.puedeFabricar(producto); }

    public boolean materiaPrimaPuedeUsar(Producto producto) { return tipo.materiaPrimaPuedeUsar(producto); }

    public void asignarConfigProduccion(ConfigProduccion configProduccion) {
        this.configProduccion = configProduccion;
        if(this.configProduccion.esValida()) configurada();
        else enConfiguracion();
    }

    public void agregarMateriaPrima(Palet paletMp) {
        configProduccion.agregarMateriaPrima(paletMp);
        if(configProduccion.esValida()) configurada();
        else enConfiguracion();
    }

    public void consumirMateriaPrima(Palet paletMp) {
        configProduccion.consumirMateriaPrima(paletMp);
        if(configProduccion.esValida()) configurada();
        else enConfiguracion();
    }

    public void asignarPalet(Palet palet) {
        this.palet = palet;
        enProduccion();
    }

    public void quitarConfigProduccion() { configProduccion = null; }

    public void quitarPalet() { palet = null; }

    public boolean noTengoConfigProduccion() { return configProduccion == null; }

    public boolean estaCambioMolde() { return estado == EstadoMaquina.CAMBIO_MOLDE; }

    public boolean estaFueraServicio() { return estado == EstadoMaquina.FUERA_SERVICIO; }

    public boolean estaMantenimiento() { return estado == EstadoMaquina.MANTENIMIENTO; }

    public boolean estaConfigurada() { return estado == EstadoMaquina.CONFIGURADA; }

    public boolean estaEnProduccion() { return estado == EstadoMaquina.EN_PRODUCCION; }

    public void enProduccion() { cambiarEstado(EstadoMaquina.EN_PRODUCCION); }

    public void configurada() { cambiarEstado(EstadoMaquina.CONFIGURADA); }

    public void enConfiguracion() { cambiarEstado(EstadoMaquina.EN_CONFIGURACION); }

    public void cambioMolde() { cambiarEstado(EstadoMaquina.CAMBIO_MOLDE); }

    public void fueraServicio() { cambiarEstado(EstadoMaquina.FUERA_SERVICIO); }

    public void mantenimiento() { cambiarEstado(EstadoMaquina.MANTENIMIENTO); }

    public void aptaProduccion() { cambiarEstado(EstadoMaquina.APTA_PRODUCCION); }

    public LoteGenerado obtenerLoteGenerado() { return new LoteGenerado(nombre); }

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

    private void cambiarEstado(EstadoMaquina estado) {
        this.estado = estado;
        this.fechaCambioEstado = LocalDateTime.now();
    }
}