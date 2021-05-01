package com.algamoney.api.dto;

import com.algamoney.api.dto.id.CategoriaIdDTO;
import com.algamoney.api.dto.id.PessoaIdDTO;
import com.algamoney.api.model.TipoLancamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LancamentoInputDTO {

    @NotNull
    @Size(min = 3, max = 50)
    private String descricao;

    @NotNull
    @JsonFormat(pattern =  "yyyy-MM-dd")
    private LocalDate dataVencimento;

    @JsonFormat(pattern =  "yyyy-MM-dd")
    private LocalDate dataPagamento;

    @NotNull
    private BigDecimal valor;

    private String observacao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;

    @Valid
    @NotNull
    private CategoriaIdDTO categoria;

    @Valid
    @NotNull
    private PessoaIdDTO pessoa;

}
