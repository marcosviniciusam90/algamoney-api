package com.algamoney.api.utils;

import com.algamoney.api.model.Categoria;
import com.github.javafaker.Faker;

public class CategoriaUtils {

    private static final Faker faker = Faker.instance();

    public static Categoria createCategoria() {
        return Categoria.builder()
                .codigo(faker.number().randomNumber())
                .nome(faker.commerce().department())
                .build();

    }
}
