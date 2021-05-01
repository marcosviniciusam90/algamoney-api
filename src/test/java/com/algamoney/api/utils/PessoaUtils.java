package com.algamoney.api.utils;

import com.algamoney.api.model.Pessoa;
import com.github.javafaker.Faker;

public class PessoaUtils {

    private static final Faker faker = Faker.instance();

    public static Pessoa createPessoa(Long codigo, boolean ativo) {
        return Pessoa.builder()
                .codigo(codigo)
                .nome(faker.superhero().name())
                .ativo(ativo)
                .endereco(EnderecoUtils.createEndereco())
                .build();

    }
}
