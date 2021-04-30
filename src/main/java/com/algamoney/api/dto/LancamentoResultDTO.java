package com.algamoney.api.dto;

import com.algamoney.api.config.CurrencyBRLSerializer;
import com.algamoney.api.model.TipoLancamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoResultDTO {

    private Long codigo;
    private String descricao;

    @JsonFormat(pattern =  "dd/MM/yyyy")
    private LocalDate dataVencimento;

    @JsonFormat(pattern =  "dd/MM/yyyy")
    private LocalDate dataPagamento;

    @JsonSerialize(using = CurrencyBRLSerializer.class)
    private BigDecimal valor;
    private String observacao;
    private TipoLancamento tipo;
    private String categoria;
    private String pessoa;
}
