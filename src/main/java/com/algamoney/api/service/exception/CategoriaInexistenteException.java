package com.algamoney.api.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoriaInexistenteException extends RuntimeException {

    public CategoriaInexistenteException(Long codigo) {
        super(String.format("Categoria com código %s não encontrada", codigo ));
    }
}
