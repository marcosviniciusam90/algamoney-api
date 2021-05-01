package com.algamoney.api.utils;

import com.algamoney.api.model.Pessoa;
import com.github.javafaker.Faker;

public class PessoaUtils {

    private static final Faker faker = Faker.instance();
    public static Pessoa createPessoa() {
        return Pessoa.builder()
                .codigo(faker.number().randomNumber())
                .nome(faker.superhero().name())
                .ativo(faker.bool().bool())
                .endereco(EnderecoUtils.createEndereco())
                .build();

    }
}
