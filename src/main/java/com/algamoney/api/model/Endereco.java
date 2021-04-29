package com.algamoney.api.model;

import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {
    @Size(min = 3, max = 50)
    private String logradouro;

    @Size(min = 1, max = 10)
    private String numero;

    @Size(min = 3, max = 50)
    private String complemento;

    @Size(min = 3, max = 50)
    private String bairro;

    @Size(min = 3, max = 15)
    private String cep;

    @Size(min = 3, max = 50)
    private String cidade;

    @Size(min = 2, max = 50)
    private String estado;
}