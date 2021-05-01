package com.algamoney.api.utils;

import com.algamoney.api.dto.CategoriaIdInputDTO;
import com.algamoney.api.dto.LancamentoInputDTO;
import com.algamoney.api.dto.PessoaIdInputDTO;
import com.algamoney.api.model.Lancamento;
import com.algamoney.api.model.TipoLancamento;
import com.github.javafaker.Faker;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LancamentoUtils {

    private static final Faker faker = Faker.instance();

    public static LancamentoInputDTO createLancamentoInputDTO() {
       return LancamentoInputDTO.builder()
                .descricao(faker.commerce().productName())
                .dataVencimento(LocalDate.now())
                .dataPagamento(LocalDate.now().minusDays(1))
                .valor(BigDecimal.valueOf(faker.number().randomDouble(2, 0, 1000)))
                .observacao(faker.shakespeare().romeoAndJulietQuote())
                .tipo(TipoLancamento.DESPESA)
                .categoria(CategoriaIdInputDTO.builder().codigo(5L).build())
                .pessoa(PessoaIdInputDTO.builder().codigo(10L).build())
                .build();
    }

    public static Lancamento createLancamento() {
        return Lancamento.builder()
                .codigo(faker.number().randomNumber())
                .descricao(faker.commerce().productName())
                .dataVencimento(LocalDate.now())
                .dataPagamento(LocalDate.now().minusDays(1))
                .valor(BigDecimal.valueOf(faker.number().randomDouble(2, 0, 1000)))
                .observacao(faker.shakespeare().kingRichardIIIQuote())
                .tipo(TipoLancamento.RECEITA)
                .categoria(CategoriaUtils.createCategoria())
                .pessoa(PessoaUtils.createPessoa())
                .build();
    }

}
