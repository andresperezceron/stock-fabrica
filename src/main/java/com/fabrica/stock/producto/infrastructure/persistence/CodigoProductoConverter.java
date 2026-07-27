package com.fabrica.stock.producto.infrastructure.persistence;

import com.fabrica.stock.producto.domain.vo.CodigoProducto;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CodigoProductoConverter implements AttributeConverter<CodigoProducto, String> {
    @Override
    public String convertToDatabaseColumn(CodigoProducto codigo) {
        return codigo == null ? null : codigo.valor();
    }

    @Override
    public CodigoProducto convertToEntityAttribute(String valor) {
        return valor == null ? null : new CodigoProducto(valor);
    }
}