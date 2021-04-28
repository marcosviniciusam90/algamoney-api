package com.algamoney.api.dto;

import com.algamoney.api.model.TipoLancamento;
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
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private BigDecimal valor;
    private String observacao;
    private TipoLancamento tipo;
    private String categoria;
    private String pessoa;
}
