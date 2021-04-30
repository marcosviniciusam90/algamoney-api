package com.algamoney.api.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class CurrencyBRLSerializer extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal valor, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {

        String valorFormatado = valor.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        valorFormatado = "R$" + valorFormatado.replace(".", ",");
        jgen.writeString(valorFormatado);

    }
}