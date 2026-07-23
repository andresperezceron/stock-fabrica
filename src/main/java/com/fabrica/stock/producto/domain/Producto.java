package com.fabrica.stock.producto.domain;

import com.fabrica.stock.familia.domain.Familia;
import com.fabrica.stock.producto.domain.constraints.ProductoConstraints;
import com.fabrica.stock.shared.domain.constraints.TextoConstraints;
import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = ProductoConstraints.CODIGO)
    private String codigo;

    @Column(nullable = false, length = TextoConstraints.MEDIO)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnidadMedida unidad;

    @ManyToOne(optional = false)
    @JoinColumn(name = "familia_id")
    private Familia familia;

    @Column(nullable = false, unique = true)
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
            String codigo,
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

    public String getCodigo() {
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

    public void activar() { activo = true; }

    public void desactivar() { activo = false; }

    public void actualizar(String codigo,
                           String descripcion,
                           UnidadMedida unidad,
                           Familia familia,
                           int piezasPorCaja,
                           TipoProducto tipo,
                           OrigenProducto origen,
                           DestinoProducto proceso)
    {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.familia = familia;
        this.piezasPorCaja = piezasPorCaja;
        this.tipo = tipo;
        this.origen = origen;
        this.destino = proceso;
    }
}