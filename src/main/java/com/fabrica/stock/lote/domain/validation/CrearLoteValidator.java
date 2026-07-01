package com.fabrica.stock.lote.domain.validation;

import com.fabrica.stock.lote.api.dto.CrearLoteRequest;
import com.fabrica.stock.lote.domain.Lote;
import com.fabrica.stock.lote.domain.OrigenLote;
import com.fabrica.stock.lote.domain.exceptions.CodigoLoteDuplicadoException;
import com.fabrica.stock.lote.domain.exceptions.LoteProveedorIsNotNull;
import com.fabrica.stock.lote.domain.exceptions.LoteProveedorObligatorioException;
import com.fabrica.stock.lote.infrastructure.LoteRepository;
import com.fabrica.stock.producto.domain.exceptions.ProductoNoEncontradoException;
import com.fabrica.stock.producto.infrastructure.ProductoRepository;
import com.fabrica.stock.shared.validation.RequestValidator;
import com.fabrica.stock.ubicacion.domain.exceptions.UbicacionNoEncontradaException;
import com.fabrica.stock.ubicacion.infrastructure.UbicacionRepository;

import java.util.Objects;

public class CrearLoteValidator extends RequestValidator<CrearLoteRequest, Lote, LoteRepository> {
    private final ProductoRepository productoRepository;
    private final UbicacionRepository ubicacionRepository;

    public CrearLoteValidator(CrearLoteRequest request, LoteRepository repository,
                              ProductoRepository productoRepository,
                              UbicacionRepository ubicacionRepository)
    {
        super(request, repository);
        this.productoRepository = productoRepository;
        this.ubicacionRepository = ubicacionRepository;
    }

    @Override
    protected void normalize() {
        request.codigoLote = request.codigoLote.trim();
        request.loteProveedor = request.loteProveedor != null ? request.loteProveedor.trim() : null;
    }

    @Override
    protected void validateBusinessRules() {
        if(repository.existsByCodigoLote(request.codigoLote)) {
            throw new CodigoLoteDuplicadoException("Codigo Lote duplicado");
        } else if(request.origenLote == OrigenLote.PROVEEDOR && request.loteProveedor == null) {
            throw new LoteProveedorObligatorioException("Lote Proveedor obligatorio");
        } else if(request.loteProveedor != null) {
            throw new LoteProveedorIsNotNull("El lote Proveedor tiene que ser nulo.");
        } else if(!productoRepository.existsById(request.productoId)) {
            throw new ProductoNoEncontradoException("Producto indicado no existe.");
        } else if(!ubicacionRepository.existsById(request.origenId)) {
            throw new UbicacionNoEncontradaException("Origen indicado no existe.");
        } else if(!ubicacionRepository.existsById(request.destinoId)) {
            throw new UbicacionNoEncontradaException("Destino indicado no existe.");
        }
    }
}