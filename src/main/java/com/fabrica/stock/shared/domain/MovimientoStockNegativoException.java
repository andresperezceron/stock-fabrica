package com.fabrica.stock.shared.domain;

public class MovimientoStockNegativoException extends RuntimeException
{
    public MovimientoStockNegativoException(String mensaje)
    {super(mensaje);}
}
