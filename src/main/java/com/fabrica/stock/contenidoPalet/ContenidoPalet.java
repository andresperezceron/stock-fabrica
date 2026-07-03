package com.fabrica.stock.contenidoPalet;

import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.palet.domain.Palet;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class ContenidoPalet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Palet palet;

    @ManyToOne(optional = false)
    private Lote lote;

    @Column(nullable = false)
    private BigDecimal cantidad;
}
