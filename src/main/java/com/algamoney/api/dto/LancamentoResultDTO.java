package com.algamoney.api.dto;

import com.algamoney.api.model.TipoLancamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoResultDTO {

    private Long codigo;
    private String descricao;

    //@JsonFormat(pattern =  "dd/MM/yyyy")
    private LocalDate dataVencimento;

    //@JsonFormat(pattern =  "dd/MM/yyyy")
    private LocalDate dataPagamento;

    //@JsonSerialize(using = CurrencyBRLSerializer.class)
    private BigDecimal valor;

    private String observacao;
    private TipoLancamento tipo;
    private String categoria;
    private String pessoa;
}
