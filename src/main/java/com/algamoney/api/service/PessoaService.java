package com.algamoney.api.service;

import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Pessoa pessoaExistente = buscarPessoaPorCodigo(codigo);
        pessoa.setCodigo(pessoaExistente.getCodigo());
        return pessoaRepository.save(pessoa);
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Pessoa pessoaExistente = buscarPessoaPorCodigo(codigo);
        pessoaExistente.setAtivo(ativo);
        pessoaRepository.save(pessoaExistente);
    }

    private Pessoa buscarPessoaPorCodigo(Long codigo) {
        Optional<Pessoa> pessoaExistente = pessoaRepository.findById(codigo);
        if(!pessoaExistente.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        return pessoaExistente.get();
    }
}
