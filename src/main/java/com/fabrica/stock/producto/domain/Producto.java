package com.fabrica.stock.producto.domain;

import com.fabrica.stock.familia.domain.Familia;
import com.fabrica.stock.producto.domain.vo.CodigoProducto;
import com.fabrica.stock.shared.domain.EntidadActivable;
import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto implements EntidadActivable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private CodigoProducto codigo;

    @Column(nullable = false, length = TextoConstraints.MEDIO)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnidadMedida unidad;

    @ManyToOne(optional = false)
    @JoinColumn(name = "familia_id")
    private Familia familia;

    @Column(nullable = false)
    private int piezasPorCaja;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoProducto tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrigenProducto origen;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DestinoProducto destino;

    @Column(nullable = false)
    private boolean activo = true;

    protected Producto() {}

    public Producto(
            CodigoProducto codigo,
            String descripcion,
            UnidadMedida unidad,
            Familia familia,
            int piezasPorCaja,
            TipoProducto tipo,
            OrigenProducto origen,
            DestinoProducto destino)
    {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.familia = familia;
        this.piezasPorCaja = piezasPorCaja;
        this.tipo = tipo;
        this.origen = origen;
        this.destino = destino;
    }

    public Long getId() {
        return id;
    }

    public CodigoProducto getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public UnidadMedida getUnidad() {
        return unidad;
    }

    public Familia getFamilia() { return familia; }

    public int getPiezasPorCaja() { return piezasPorCaja; }

    public TipoProducto getTipo() { return tipo; }

    public OrigenProducto getOrigen() { return origen; }

    public DestinoProducto getDestino() { return destino; }

    public boolean estoyActivo() { return activo; }

    public boolean soyTipoTerminado() { return tipo == TipoProducto.TERMINADO; }

    public boolean soyTipoIntermedio() { return tipo == TipoProducto.INTERMEDIO; }

    public boolean soyTipoMateriaPrima() { return tipo == TipoProducto.MATERIA_PRIMA; }

    public boolean esOrigenProduccionPropia() { return origen == OrigenProducto.PRODUCCION_PROPIA; }

    public boolean esOrigenCompra() { return origen == OrigenProducto.COMPRA; }

    public boolean esDestinoNinguno() { return destino == DestinoProducto.NINGUNO; }

    public boolean esDestinoMontaje() { return destino == DestinoProducto.MONTAJE; }

    public boolean esDestinoInyeccion() { return destino == DestinoProducto.INYECCION; }

    public boolean esDestinoExtrusion() { return destino == DestinoProducto.EXTRUSION; }

    public boolean esDestinoInyExt() { return destino == DestinoProducto.INYECCION_O_EXTRUSION; }

    public boolean consumeInyeccion() {
        return soyTipoMateriaPrima() && esDestinoInyeccion();
    }

    public boolean produceInyeccion() {
        return (soyTipoTerminado() || soyTipoIntermedio()) && esDestinoInyeccion();
    }

    @Override public boolean estaActiva() { return activo; }

    @Override public void activar() { activo = true; }

    @Override public void desactivar() { activo = false; }

    public void actualizar(CodigoProducto codigo,
                           String descripcion,
                           UnidadMedida unidad,
                           Familia familia,
                           int piezasPorCaja,
                           TipoProducto tipo,
                           OrigenProducto origen,
                           DestinoProducto destino)
    {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.familia = familia;
        this.piezasPorCaja = piezasPorCaja;
        this.tipo = tipo;
        this.origen = origen;
        this.destino = destino;
    }
}