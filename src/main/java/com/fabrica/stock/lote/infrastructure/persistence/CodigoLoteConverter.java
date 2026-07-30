package com.fabrica.stock.lote.infrastructure.persistence;

import com.fabrica.stock.lote.domain.vo.CodigoLote;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CodigoLoteConverter implements AttributeConverter<CodigoLote, String> {
    @Override
    public String convertToDatabaseColumn(CodigoLote codigoLote) {
        return "";
    }

    @Override
    public CodigoLote convertToEntityAttribute(String valor) {
        return null;
    }
}
