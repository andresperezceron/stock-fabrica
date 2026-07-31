package com.fabrica.stock.lote.domain.exceptions;

import com.fabrica.stock.shared.domain.DomainErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LoteExceptionHandler {

    @ExceptionHandler(LoteNoEncontradoException.class)
    public ResponseEntity<DomainErrorResponse> manjarLoteNoEncontrado(LoteNoEncontradoException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(CodigoLoteDuplicadoException.class)
    public ResponseEntity<DomainErrorResponse> manejarCodigoLoteDuplicado(CodigoLoteDuplicadoException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(LoteProveedorObligatorioException.class)
    public ResponseEntity<DomainErrorResponse> manejarLoteProveedorObligatorio(LoteProveedorObligatorioException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(LoteProveedorIsNotNullException.class)
    public ResponseEntity<DomainErrorResponse> manejarLoteProveedorIsNotNull(LoteProveedorIsNotNullException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(CodigoLoteNoValidoException.class)
    public ResponseEntity<DomainErrorResponse> manejarCodigoLoteNoValido(CodigoLoteNoValidoException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(DomainErrorResponse.of(ex.getMessage()));
    }
}