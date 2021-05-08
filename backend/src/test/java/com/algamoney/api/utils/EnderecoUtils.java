package com.algamoney.api.utils;

import com.algamoney.api.model.Endereco;
import com.github.javafaker.Faker;

public class EnderecoUtils {

    private static final Faker FAKER = Faker.instance();

    public static Endereco createEndereco() {
        return Endereco.builder()
                .logradouro(FAKER.address().streetAddress())
                .numero(FAKER.address().buildingNumber())
                .complemento(FAKER.address().streetAddressNumber())
                .bairro(FAKER.address().secondaryAddress())
                .cep(FAKER.address().zipCode())
                .cidade(FAKER.address().city())
                .estado(FAKER.address().state())
                .build();
    }
}
