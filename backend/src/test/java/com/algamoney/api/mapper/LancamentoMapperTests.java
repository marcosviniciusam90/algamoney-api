package com.algamoney.api.mapper;

import com.algamoney.api.dto.LancamentoInputDTO;
import com.algamoney.api.dto.LancamentoResponseDTO;
import com.algamoney.api.model.Lancamento;
import org.junit.jupiter.api.Test;

import static com.algamoney.api.utils.LancamentoUtils.createLancamento;
import static com.algamoney.api.utils.LancamentoUtils.createLancamentoInputDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LancamentoMapperTests {

    private static final LancamentoMapper LANCAMENTO_MAPPER = LancamentoMapper.INSTANCE;

    @Test
    void givenInputDTOMapToEntity() {
        LancamentoInputDTO lancamentoInputDTO = createLancamentoInputDTO();
        Lancamento lancamento = LANCAMENTO_MAPPER.inputDTOToEntity(lancamentoInputDTO);

        assertInputDTOMapToEntity(lancamentoInputDTO, lancamento);
    }

    @Test
    void givenEntityMapToResponseDTO() {
        Lancamento lancamento = createLancamento();
        LancamentoResponseDTO lancamentoResponseDTO = LANCAMENTO_MAPPER.entityToResponseDTO(lancamento);

        assertEntityMapToResponseDTO(lancamento, lancamentoResponseDTO);
    }

    private void assertEntityMapToResponseDTO(Lancamento lancamento, LancamentoResponseDTO lancamentoResponseDTO) {
        assertEquals(lancamento.getCodigo(), lancamentoResponseDTO.getCodigo());
        assertEquals(lancamento.getDescricao(), lancamentoResponseDTO.getDescricao());
        assertEquals(lancamento.getDataVencimento(), lancamentoResponseDTO.getDataVencimento());
        assertEquals(lancamento.getDataPagamento(), lancamentoResponseDTO.getDataPagamento());
        assertEquals(lancamento.getValor(), lancamentoResponseDTO.getValor());
        assertEquals(lancamento.getObservacao(), lancamentoResponseDTO.getObservacao());
        assertEquals(lancamento.getTipo(), lancamentoResponseDTO.getTipo());
        assertEquals(lancamento.getCategoria().getNome(), lancamentoResponseDTO.getCategoria());
        assertEquals(lancamento.getPessoa().getNome(), lancamentoResponseDTO.getPessoa());
    }

    private void assertInputDTOMapToEntity(LancamentoInputDTO lancamentoInputDTO, Lancamento lancamento) {
        assertEquals(lancamentoInputDTO.getDescricao(), lancamento.getDescricao());
        assertEquals(lancamentoInputDTO.getDataVencimento(), lancamento.getDataVencimento());
        assertEquals(lancamentoInputDTO.getDataPagamento(), lancamento.getDataPagamento());
        assertEquals(lancamentoInputDTO.getValor(), lancamento.getValor());
        assertEquals(lancamentoInputDTO.getObservacao(), lancamento.getObservacao());
        assertEquals(lancamentoInputDTO.getTipo(), lancamento.getTipo());
        assertEquals(lancamentoInputDTO.getCategoria().getCodigo(), lancamento.getCategoria().getCodigo());
        assertEquals(lancamentoInputDTO.getPessoa().getCodigo(), lancamento.getPessoa().getCodigo());
    }
}
