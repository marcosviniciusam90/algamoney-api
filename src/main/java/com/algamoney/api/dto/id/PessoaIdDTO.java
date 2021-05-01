package com.algamoney.api.dto.id;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaIdDTO {

    @NotNull
    private Long codigo;

}
