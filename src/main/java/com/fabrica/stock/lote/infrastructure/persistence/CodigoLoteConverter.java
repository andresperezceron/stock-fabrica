package com.fabrica.stock.lote.infrastructure.persistence;

import com.fabrica.stock.lote.domain.vo.CodigoLote;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CodigoLoteConverter implements AttributeConverter<CodigoLote, String> {
    @Override
    public String convertToDatabaseColumn(CodigoLote codigo) {
        return codigo == null ? null : codigo.valor() ;
    }

    @Override
    public CodigoLote convertToEntityAttribute(String valor) {
        return valor == null ? null : new CodigoLote(valor);
    }
}
