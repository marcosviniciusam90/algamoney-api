package com.algamoney.api.service;

import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.PessoaRepository;
import com.algamoney.api.service.exception.PessoaInexistenteException;
import com.algamoney.api.utils.PessoaUtils;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTests {

    private static final Faker faker = Faker.instance();

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @Test
    void testBuscarPessoaInexistente() {

        Long codigoPessoa = faker.number().randomNumber();

        when(pessoaRepository.findById(codigoPessoa)).thenReturn(Optional.empty());

        assertThrows(PessoaInexistenteException.class, () -> pessoaService.findById(codigoPessoa));
    }

    @Test
    void testAtualizarComSucesso() {
        Long codigoPessoa = faker.number().randomNumber();
        Pessoa pessoa = PessoaUtils.createPessoa(codigoPessoa, true);
        Pessoa pessoaExistente = PessoaUtils.createPessoa(codigoPessoa, false);

        when(pessoaRepository.findById(codigoPessoa)).thenReturn(Optional.of(pessoaExistente));

        pessoaService.atualizar(codigoPessoa, pessoa);

        verify(pessoaRepository, times(1)).save(pessoa);

    }

    @Test
    void testAtualizarPessoaInexistente() {
        Long codigoPessoa = faker.number().randomNumber();
        Pessoa pessoa = PessoaUtils.createPessoa(codigoPessoa, true);

        when(pessoaRepository.findById(codigoPessoa)).thenReturn(Optional.empty());

        assertThrows(PessoaInexistenteException.class, () -> pessoaService.atualizar(codigoPessoa, pessoa));

    }

    @Test
    void testAtualizarPropriedadeAtivoComSucesso() {
        Long codigoPessoa = faker.number().randomNumber();
        Boolean ativo = true;

        Pessoa pessoaExistente = PessoaUtils.createPessoa(codigoPessoa, false);

        when(pessoaRepository.findById(codigoPessoa)).thenReturn(Optional.of(pessoaExistente));

        pessoaService.atualizarPropriedadeAtivo(codigoPessoa, ativo);

        verify(pessoaRepository, times(1)).save(pessoaExistente);

        assertTrue(pessoaExistente.getAtivo());

    }


}
