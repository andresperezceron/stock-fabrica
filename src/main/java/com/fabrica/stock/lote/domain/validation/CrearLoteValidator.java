package com.fabrica.stock.lote.domain.validation;

import com.fabrica.stock.lote.api.dto.CrearLote;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.lote.domain.exceptions.LoteProveedorIsNotNull;
import com.fabrica.stock.lote.domain.exceptions.LoteProveedorObligatorioException;
import com.fabrica.stock.lote.infrastructure.LoteRepository;
import com.fabrica.stock.producto.application.ObtenerProductoService;
import com.fabrica.stock.shared.validation.RequestValidator;

public class CrearLoteValidator extends RequestValidator<CrearLote, Lote, LoteRepository> {
    private final ObtenerProductoService obtenerProductoService;

    public CrearLoteValidator(
            CrearLote request,
            LoteRepository repository,
            ObtenerProductoService obtenerProductoService)
    {
        super(request, repository);
        this.obtenerProductoService = obtenerProductoService;
    }

    @Override
    protected void normalize() {
        request.loteProveedor = request.loteProveedor != null ? request.loteProveedor.trim() : null;
    }

    @Override
    protected void validateBusinessRules() {
        if(request.origenLote == OrigenLote.PROVEEDOR && request.loteProveedor == null) {
            throw new LoteProveedorObligatorioException("Lote Proveedor obligatorio");
        } else if(request.origenLote == OrigenLote.PRODUCCION && request.loteProveedor != null) {
            throw new LoteProveedorIsNotNull("El lote Proveedor tiene que ser nulo.");
        } else obtenerProductoService.obtener(request.productoId); /* Lanza exception si id no existe */
    }
}