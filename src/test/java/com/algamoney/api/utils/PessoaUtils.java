package com.algamoney.api.utils;

import com.algamoney.api.model.Pessoa;
import com.github.javafaker.Faker;

public class PessoaUtils {

    private static final Faker faker = Faker.instance();

    public static Pessoa createPessoa(Long codigo, boolean ativo) {
        return Pessoa.builder()
                .codigo(codigo)
                .nome(faker.superhero().name())
                .cpf(faker.regexify("[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}"))
                .ativo(ativo)
                .endereco(EnderecoUtils.createEndereco())
                .build();

    }
}
