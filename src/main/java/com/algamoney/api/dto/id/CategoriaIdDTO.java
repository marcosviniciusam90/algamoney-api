package com.algamoney.api.dto.id;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaIdDTO {

    @NotNull
    private Long codigo;

}
