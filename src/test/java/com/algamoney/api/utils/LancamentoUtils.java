package com.algamoney.api.utils;

import com.algamoney.api.dto.LancamentoInputDTO;
import com.algamoney.api.dto.LancamentoResponseDTO;
import com.algamoney.api.dto.id.CategoriaIdDTO;
import com.algamoney.api.dto.id.PessoaIdDTO;
import com.algamoney.api.model.Lancamento;
import com.algamoney.api.model.TipoLancamento;
import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LancamentoUtils {

    private static final Faker FAKER = Faker.instance();

    public static LancamentoInputDTO createLancamentoInputDTO() {
       return LancamentoInputDTO.builder()
                .descricao(FAKER.commerce().productName())
                .dataVencimento(LocalDate.now())
                .dataPagamento(LocalDate.now().minusDays(1))
                .valor(BigDecimal.valueOf(FAKER.number().randomDouble(2, 0, 1000)))
                .observacao(FAKER.shakespeare().romeoAndJulietQuote())
                .tipo(TipoLancamento.DESPESA)
                .categoria(CategoriaIdDTO.builder().codigo(5L).build())
                .pessoa(PessoaIdDTO.builder().codigo(10L).build())
                .build();
    }

    public static LancamentoResponseDTO createLancamentoResponseDTO() {
        return LancamentoResponseDTO.builder()
                .codigo(FAKER.number().randomNumber())
                .descricao(FAKER.commerce().productName())
                .dataVencimento(LocalDate.now())
                .dataPagamento(LocalDate.now().minusDays(1))
                .valor(BigDecimal.valueOf(FAKER.number().randomDouble(2, 0, 1000)))
                .observacao(FAKER.shakespeare().romeoAndJulietQuote())
                .tipo(TipoLancamento.DESPESA)
                .categoria(FAKER.commerce().department())
                .pessoa(FAKER.dragonBall().character())
                .build();
    }

    public static Lancamento createLancamento() {
        return Lancamento.builder()
                .codigo(FAKER.number().randomNumber())
                .descricao(FAKER.commerce().productName())
                .dataVencimento(LocalDate.now())
                .dataPagamento(LocalDate.now().minusDays(1))
                .valor(BigDecimal.valueOf(FAKER.number().randomDouble(2, 0, 1000)))
                .observacao(FAKER.shakespeare().kingRichardIIIQuote())
                .tipo(TipoLancamento.RECEITA)
                .categoria(CategoriaUtils.createCategoria(FAKER.number().randomNumber()))
                .pessoa(PessoaUtils.createPessoa(FAKER.number().randomNumber(), true))
                .build();
    }

}
