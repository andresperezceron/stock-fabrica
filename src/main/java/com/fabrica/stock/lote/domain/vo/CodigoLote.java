package com.fabrica.stock.lote.domain.vo;

import com.fabrica.stock.lote.domain.TurnoProduccion;
import com.fabrica.stock.maquina.domain.constraints.MaquinaConstraints;
import com.fabrica.stock.shared.domain.exceptions.RecursoNoValidoException;

import java.util.regex.Pattern;

public record CodigoLote(String valor) {
    private static final String MAQUINA = "[A-Z]{" + MaquinaConstraints.NOMBRE + "}";
    private static final String FECHA = "\\d{5}";

    private static final Pattern PATRON =
            Pattern.compile("^"
                    + MAQUINA
                    + "-"
                    + FECHA
                    + "-("
                    + TurnoProduccion.regex()
                    + ")$");

    public CodigoLote {
        if(valor == null || !PATRON.matcher(valor).matches())
            throw new RecursoNoValidoException("Código de lote no válido: " + valor);
    }

    public static CodigoLote of(String valor) {
        return new CodigoLote(valor);
    }
}