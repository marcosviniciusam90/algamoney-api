package com.algamoney.api.service.exception;

public class LancamentoInexistenteException extends RuntimeException{

    public LancamentoInexistenteException(Long codigo) {
        super(String.format("Lançamento com código %s não encontrado", codigo ));
    }
}
