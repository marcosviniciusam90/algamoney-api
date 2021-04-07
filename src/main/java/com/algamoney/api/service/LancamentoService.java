package com.algamoney.api.service;

import com.algamoney.api.model.Lancamento;
import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.LancamentoRepository;
import com.algamoney.api.repository.PessoaRepository;
import com.algamoney.api.service.exception.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Lancamento atualizar(Long codigo, Lancamento lancamento) {

        Lancamento lancamentoExistente = buscaLancamentoPorCodigo(codigo);
        lancamento.setCodigo(lancamentoExistente.getCodigo());
        return lancamentoRepository.save(lancamento);

    }

    private Lancamento buscaLancamentoPorCodigo(Long codigo) {
        Optional<Lancamento> lancamentoExistente = lancamentoRepository.findById(codigo);
        if(!lancamentoExistente.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        return lancamentoExistente.get();
    }

    public Lancamento salvar(Lancamento lancamento) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
        if(!pessoaOptional.isPresent() || pessoaOptional.get().isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }

        return lancamentoRepository.save(lancamento);

    }
}
