package com.fabrica.stock.maquinainyeccion.infrastructure.persistence;

import com.fabrica.stock.maquinainyeccion.domain.vo.NombreMaquina;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class NombreMaquinaConverter implements AttributeConverter<NombreMaquina, String> {
    @Override
    public String convertToDatabaseColumn(NombreMaquina nombre) {
        return nombre == null ? null : nombre.getValor();
    }

    @Override
    public NombreMaquina convertToEntityAttribute(String valor) {
        return valor == null ? null : new NombreMaquina(valor);
    }
}