package com.algamoney.api.service;

import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.PessoaRepository;
import com.algamoney.api.service.exception.PessoaInexistenteException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public Pessoa atualizar(Long codigo, Pessoa pessoa) {
        Pessoa pessoaExistente = findById(codigo);
        pessoa.setCodigo(pessoaExistente.getCodigo());
        return pessoaRepository.save(pessoa);
    }

    public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
        Pessoa pessoaExistente = findById(codigo);
        pessoaExistente.setAtivo(ativo);
        pessoaRepository.save(pessoaExistente);
    }

    public Pessoa findById(Long codigo) {
        return pessoaRepository.findById(codigo)
                .orElseThrow(() -> new PessoaInexistenteException(codigo));
    }
}
