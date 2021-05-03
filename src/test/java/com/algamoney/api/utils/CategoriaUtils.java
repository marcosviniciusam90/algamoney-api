package com.algamoney.api.utils;

import com.algamoney.api.model.Categoria;
import com.github.javafaker.Faker;

public class CategoriaUtils {

    private static final Faker FAKER = Faker.instance();

    public static Categoria createCategoria(Long codigo) {
        return Categoria.builder()
                .codigo(codigo)
                .nome(FAKER.commerce().department())
                .build();

    }
}
