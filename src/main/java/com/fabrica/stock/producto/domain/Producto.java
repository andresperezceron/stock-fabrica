package com.fabrica.stock.producto.domain;

import com.fabrica.stock.familia.domain.Familia;
import com.fabrica.stock.producto.domain.vo.CodigoProducto;
import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {
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

    public boolean estaActivo() { return activo; }

    public boolean esDestinoInyeccion() {
        return destino == DestinoProducto.INYECCION ||
                destino == DestinoProducto.INYECCION_O_EXTRUSION;
    }

    public boolean esDestinoExtrusion() {
        return destino == DestinoProducto.EXTRUSION ||
                destino == DestinoProducto.INYECCION_O_EXTRUSION;
    }

    public boolean esDestinoInyeccionOExtrusion() {
        return destino == DestinoProducto.INYECCION_O_EXTRUSION;
    }

    public boolean esMateriaPrima() { return tipo == TipoProducto.MATERIA_PRIMA; }

    public void activar() { activo = true; }

    public void desactivar() { activo = false; }

    public boolean mismaEntidadQue(Producto producto) {
        return producto != null && this.id.equals(producto.getId());
    }

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