package com.fabrica.stock.lote.domain.constraints;

import com.fabrica.stock.lote.domain.TurnoProduccion;
import com.fabrica.stock.maquina.domain.constraints.MaquinaConstraints;

import java.util.regex.Pattern;

public final class LoteConstraints {
    private static final String MAQUINA = "[A-Z]{" + MaquinaConstraints.NOMBRE + "}";
    private static final String FECHA = "\\d{5}";
    private static final String TURNO = "[A-Z]";
    private static final Pattern CODIGO_LOTE =
            Pattern.compile("^"
                            + MAQUINA
                            + "-"
                            + FECHA
                            + "-("
                            + TurnoProduccion.regex()
                            + ")$");

    public static boolean esCodigoLoteValido(String codigo) {
        return codigo != null && CODIGO_LOTE.matcher(codigo).matches();
    }
}