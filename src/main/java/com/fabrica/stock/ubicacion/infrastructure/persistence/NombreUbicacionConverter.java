package com.fabrica.stock.ubicacion.infrastructure.persistence;

import com.fabrica.stock.ubicacion.domain.vo.NombreUbicacion;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class NombreUbicacionConverter implements AttributeConverter<NombreUbicacion, String> {
    @Override
    public String convertToDatabaseColumn(NombreUbicacion nombre) {
        return nombre == null ? null : nombre.valor();
    }

    @Override
    public NombreUbicacion convertToEntityAttribute(String valor) {
        return valor == null ? null : new NombreUbicacion(valor);
    }
}