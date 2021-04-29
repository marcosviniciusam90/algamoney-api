package com.algamoney.api.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PessoaInativaException extends RuntimeException {

    public PessoaInativaException(Long codigo) {
        super(String.format("Pessoa com código %s está inativa", codigo ));
    }

}
