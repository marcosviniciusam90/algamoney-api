package com.algamoney.api.utils;

import com.algamoney.api.model.Endereco;
import com.github.javafaker.Faker;

public class EnderecoUtils {

    private static final Faker faker = Faker.instance();

    public static Endereco createEndereco() {
        return Endereco.builder()
                .logradouro(faker.address().streetAddress())
                .numero(faker.address().buildingNumber())
                .complemento(faker.address().streetAddressNumber())
                .bairro(faker.address().secondaryAddress())
                .cep(faker.address().zipCode())
                .cidade(faker.address().city())
                .estado(faker.address().state())
                .build();
    }
}
