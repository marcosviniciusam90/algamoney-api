package com.algamoney.api.service;

import com.algamoney.api.model.Categoria;
import com.algamoney.api.model.Lancamento;
import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.CategoriaRepository;
import com.algamoney.api.repository.LancamentoRepository;
import com.algamoney.api.repository.PessoaRepository;
import com.algamoney.api.service.exception.CategoriaInexistenteException;
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

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Lancamento atualizar(Long codigo, Lancamento lancamento) {

        if(!isPessoaValida(lancamento.getPessoa().getCodigo())) {
            throw new PessoaInexistenteOuInativaException();
        }

        if(!isCategoriaValida(lancamento.getCategoria().getCodigo())) {
            throw new CategoriaInexistenteException();
        }

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

        if(!isPessoaValida(lancamento.getPessoa().getCodigo())) {
            throw new PessoaInexistenteOuInativaException();
        }

        if(!isCategoriaValida(lancamento.getCategoria().getCodigo())) {
            throw new CategoriaInexistenteException();
        }

        return lancamentoRepository.save(lancamento);

    }

    private boolean isPessoaValida(Long codigo) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(codigo);
        return pessoaOptional.isPresent() && pessoaOptional.get().isAtivo();
    }

    private boolean isCategoriaValida(Long codigo) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(codigo);
        return categoriaOptional.isPresent();
    }
}
