package com.algamoney.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permissao")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Permissao {

    @Id
    private Long codigo;

    private String descricao;
}
