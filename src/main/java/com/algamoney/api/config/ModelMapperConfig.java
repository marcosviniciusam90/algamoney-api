package com.algamoney.api.config;

import com.algamoney.api.dto.LancamentoResultDTO;
import com.algamoney.api.model.Lancamento;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(lancamentoMap());
        return modelMapper;
    }

    private PropertyMap<Lancamento, LancamentoResultDTO> lancamentoMap() {
        return new PropertyMap<Lancamento, LancamentoResultDTO>() {
            @Override
            protected void configure() {
                map().setCategoria(source.getCategoria().getNome());
                map().setPessoa(source.getPessoa().getNome());
            }
        };
    }

}
