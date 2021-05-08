package com.algamoney.api.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PessoaInexistenteException extends RuntimeException{

    public PessoaInexistenteException(Long codigo) {
        super(String.format("Pessoa com código %s não encontrada", codigo ));
    }
}
