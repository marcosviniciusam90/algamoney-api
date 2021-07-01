package com.algamoney.api.mapper;

import com.algamoney.api.dto.LancamentoInputDTO;
import com.algamoney.api.dto.LancamentoResponseDTO;
import com.algamoney.api.dto.LancamentoResumoDTO;
import com.algamoney.api.model.Lancamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LancamentoMapper {

    LancamentoMapper INSTANCE = Mappers.getMapper(LancamentoMapper.class);

    @Mapping(target = "codigo", ignore = true)
    @Mapping(target = "categoria.nome", ignore = true)
    @Mapping(target = "pessoa.nome", ignore = true)
    @Mapping(target = "pessoa.ativo", ignore = true)
    @Mapping(target = "pessoa.endereco", ignore = true)
    @Mapping(target = "pessoa.cpf", ignore = true)
    Lancamento inputDTOToEntity(LancamentoInputDTO inputDTO);

    @Mapping(source = "pessoa.nome", target = "pessoa")
    @Mapping(source = "categoria.nome", target = "categoria")
    LancamentoResumoDTO entityToResumoDTO(Lancamento entity);

    LancamentoResponseDTO entityToResponseDTO(Lancamento entity);
}
