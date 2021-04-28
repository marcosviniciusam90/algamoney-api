package com.algamoney.api.service;

import com.algamoney.api.dto.LancamentoInputDTO;
import com.algamoney.api.dto.LancamentoResultDTO;
import com.algamoney.api.model.Categoria;
import com.algamoney.api.model.Lancamento;
import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.CategoriaRepository;
import com.algamoney.api.repository.LancamentoRepository;
import com.algamoney.api.repository.PessoaRepository;
import com.algamoney.api.service.exception.CategoriaInexistenteException;
import com.algamoney.api.service.exception.PessoaInexistenteOuInativaException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public LancamentoResultDTO atualizar(Long codigo, LancamentoInputDTO lancamentoInputDTO) {
        Lancamento lancamentoInput = modelMapper.map(lancamentoInputDTO, Lancamento.class);

        verifyAndSelectLancamento(codigo, lancamentoInput);
        verifyAndSetPersonIfValid(lancamentoInput);
        verifyAndSetCategoryIfValid(lancamentoInput);

        Lancamento lancamentoSalvo = lancamentoRepository.save(lancamentoInput);
        return modelMapper.map(lancamentoSalvo, LancamentoResultDTO.class);

    }

    public LancamentoResultDTO salvar(LancamentoInputDTO lancamentoInputDTO) {

        Lancamento lancamentoInput = modelMapper.map(lancamentoInputDTO, Lancamento.class);

        verifyAndSetPersonIfValid(lancamentoInput);
        verifyAndSetCategoryIfValid(lancamentoInput);

        Lancamento lancamentoSalvo = lancamentoRepository.save(lancamentoInput);
        return modelMapper.map(lancamentoSalvo, LancamentoResultDTO.class);

    }

    private void verifyAndSelectLancamento(Long codigo, Lancamento lancamentoInput) {
        boolean existeLancamento = lancamentoRepository.existsById(codigo);
        if(existeLancamento) {
            lancamentoInput.setCodigo(codigo);
        } else {
            throw new EmptyResultDataAccessException(1);
        }

    }

    private void verifyAndSetCategoryIfValid(Lancamento lancamento) {
        Optional<Categoria> categoria = categoriaRepository.findById(lancamento.getCategoria().getCodigo());
        if(categoria.isPresent()) {
            lancamento.setCategoria(categoria.get());
        } else {
            throw new CategoriaInexistenteException();
        }
    }

    private void verifyAndSetPersonIfValid(Lancamento lancamento) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());

        if(pessoa.isPresent() && pessoa.get().isAtivo()) {
            lancamento.setPessoa(pessoa.get());
        } else {
            throw new PessoaInexistenteOuInativaException();
        }
    }
}
