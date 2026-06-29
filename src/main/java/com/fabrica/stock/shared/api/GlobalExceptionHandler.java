package com.fabrica.stock.shared.api;

import com.fabrica.stock.shared.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /* Recurso no encontrado */
    @ExceptionHandler(RecursoNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String manejarNoEncontrado(RecursoNoEncontradoException ex) { return ex.getMessage(); }

    /* movimiento stockNegativo */
    @ExceptionHandler(MovimientoStockNegativoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String manejarStockNegativo(MovimientoStockNegativoException ex) { return ex.getMessage(); }

    /* Ubicacion unique key */
    @ExceptionHandler(NombreUbicacionDuplicadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String manejarNombreUbicacionDuplicado(NombreUbicacionDuplicadoException ex) { return ex.getMessage(); }

    /* Ubicacion null */
    @ExceptionHandler(NombreUbicacionNullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String manejarNombreUbicacionNull(NombreUbicacionNullException ex) { return ex.getMessage(); }

    /* Ubicacion maxLength */
    @ExceptionHandler(NombreUbicacionMaxLengthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String manejarNombreUbicacionMaxLength(NombreUbicacionMaxLengthException ex) { return ex.getMessage(); }

    /* Ubicacion isBlanck */
    @ExceptionHandler(NombreUbicacionIsBlanckException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String manejarNombreUbicacionBlanck(NombreUbicacionIsBlanckException ex) { return ex.getMessage(); }

    /* Producto unique key */
    @ExceptionHandler(CodigoProductoDuplicadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String manejarCodigoProductoDuplicado(CodigoProductoDuplicadoException ex) { return ex.getMessage(); }

    /* Producto null */
    @ExceptionHandler(CodigoProductoNullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String manejarCodigoProductoNull(CodigoProductoNullException ex) { return ex.getMessage(); }

    /* Producto isBlanck */
    @ExceptionHandler(CodigoProductoIsBlanckException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String manejarCodigoProductoIsBlanck(CodigoProductoIsBlanckException ex) { return ex.getMessage(); }

    /* Producto maxLength */
    @ExceptionHandler(CodigoProductoMaxLengthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String manejarCodigoProductoMaxLength(CodigoProductoMaxLengthException ex) { return ex.getMessage(); }
}