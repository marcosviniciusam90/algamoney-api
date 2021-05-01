package com.algamoney.api.service;

import com.algamoney.api.repository.PessoaRepository;
import com.algamoney.api.service.exception.PessoaInexistenteException;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PessoaServiceTests {

    private static final Faker faker = Faker.instance();

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @Test
    void quandoNaoEncontrarPessoaPeloCodigoDeveRetornarExcecao() {

        Long codigoPessoa = faker.number().randomNumber();

        when(pessoaRepository.findById(codigoPessoa)).thenReturn(Optional.empty());

        assertThrows(PessoaInexistenteException.class, () -> pessoaService.findById(codigoPessoa));
    }


}
