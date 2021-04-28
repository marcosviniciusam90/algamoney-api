package com.algamoney.api.dto;

import com.algamoney.api.model.TipoLancamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoInputDTO {

    @NotBlank
    @Size(max = 50)
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
    private CategoriaIdInputDTO categoria;

    @Valid
    @NotNull
    private PessoaIdInputDTO pessoa;

}
