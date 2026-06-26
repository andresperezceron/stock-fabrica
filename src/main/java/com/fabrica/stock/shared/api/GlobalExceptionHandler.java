package com.fabrica.stock.shared.api;

import com.fabrica.stock.shared.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String manejarNoEncontrado(RecursoNoEncontradoException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(MovimientoStockNegativoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String manejarStockNegativo(MovimientoStockNegativoException ex) { return ex.getMessage(); }

    /* Ubicacion */
    @ExceptionHandler(NombreUbicacionDuplicadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String manejarNombreUbicacionDuplicado(NombreUbicacionDuplicadoException ex) { return ex.getMessage(); }

    /* Ubicacion */
    @ExceptionHandler(NombreUbicacionNullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String manejarNombreUbicacionNull(NombreUbicacionNullException ex) { return ex.getMessage(); }

    /* Ubicacion */
    @ExceptionHandler(NombreUbicacionMaxLengthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String manejarNombreUbicacionMaxLength(NombreUbicacionMaxLengthException ex) { return ex.getMessage(); }

    /* Producto */
    @ExceptionHandler(CodigoProductoDuplicadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String manejarCodigoProductoDuplicado(CodigoProductoDuplicadoException ex) { return ex.getMessage(); }

    /* Producto */
    @ExceptionHandler(CodigoProductoNullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String manejarCodigoProductoNull(CodigoProductoNullException ex) { return ex.getMessage(); }
}