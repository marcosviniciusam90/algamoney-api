package com.algamoney.api.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaIdInputDTO {

    @NotNull
    private Long codigo;

}
