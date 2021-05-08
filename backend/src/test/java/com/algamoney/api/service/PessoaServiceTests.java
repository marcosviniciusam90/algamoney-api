package com.algamoney.api.service;

import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.PessoaRepository;
import com.algamoney.api.service.exception.PessoaInexistenteException;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.algamoney.api.utils.PessoaUtils.createPessoa;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTests {

    private static final Faker FAKER = Faker.instance();

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @Test
    void testBuscarPessoaInexistente() {

        Long codigoPessoa = FAKER.number().randomNumber();

        when(pessoaRepository.findById(codigoPessoa)).thenReturn(Optional.empty());

        assertThrows(PessoaInexistenteException.class, () -> pessoaService.findById(codigoPessoa));
    }

    @Test
    void testAtualizarComSucesso() {
        Long codigoPessoa = FAKER.number().randomNumber();
        Pessoa pessoa = createPessoa(codigoPessoa, true);
        Pessoa pessoaExistente = createPessoa(codigoPessoa, false);

        when(pessoaRepository.findById(codigoPessoa)).thenReturn(Optional.of(pessoaExistente));

        pessoaService.atualizar(codigoPessoa, pessoa);

        verify(pessoaRepository, times(1)).save(pessoa);

    }

    @Test
    void testAtualizarPessoaInexistente() {
        Long codigoPessoa = FAKER.number().randomNumber();
        Pessoa pessoa = createPessoa(codigoPessoa, true);

        when(pessoaRepository.findById(codigoPessoa)).thenReturn(Optional.empty());

        assertThrows(PessoaInexistenteException.class, () -> pessoaService.atualizar(codigoPessoa, pessoa));

    }

    @Test
    void testAtualizarPropriedadeAtivoComSucesso() {
        Long codigoPessoa = FAKER.number().randomNumber();
        Boolean ativo = true;

        Pessoa pessoaExistente = createPessoa(codigoPessoa, false);

        when(pessoaRepository.findById(codigoPessoa)).thenReturn(Optional.of(pessoaExistente));

        pessoaService.atualizarPropriedadeAtivo(codigoPessoa, ativo);

        verify(pessoaRepository, times(1)).save(pessoaExistente);

        assertTrue(pessoaExistente.getAtivo());

    }


}
