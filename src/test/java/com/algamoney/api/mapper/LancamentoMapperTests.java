package com.algamoney.api.mapper;

import com.algamoney.api.dto.LancamentoInputDTO;
import com.algamoney.api.dto.LancamentoResultDTO;
import com.algamoney.api.model.Lancamento;
import org.junit.jupiter.api.Test;

import static com.algamoney.api.utils.LancamentoUtils.createLancamento;
import static com.algamoney.api.utils.LancamentoUtils.createLancamentoInputDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LancamentoMapperTests {

    private static final LancamentoMapper lancamentoMapper = LancamentoMapper.INSTANCE;

    @Test
    void givenInputDTOMapToEntity() {
        LancamentoInputDTO inputDTO = createLancamentoInputDTO();
        Lancamento lancamento = lancamentoMapper.inputDTOToEntity(inputDTO);

        assertInputDTOMapToEntity(inputDTO, lancamento);
    }

    @Test
    void givenEntityMapToResultDTO() {
        Lancamento lancamento = createLancamento();
        LancamentoResultDTO resultDTO = lancamentoMapper.entityToResultDTO(lancamento);

        assertEntityMapToResultDTO(lancamento, resultDTO);
    }

    private void assertEntityMapToResultDTO(Lancamento lancamento, LancamentoResultDTO resultDTO) {
        assertEquals(lancamento.getCodigo(), resultDTO.getCodigo());
        assertEquals(lancamento.getDescricao(), resultDTO.getDescricao());
        assertEquals(lancamento.getDataVencimento(), resultDTO.getDataVencimento());
        assertEquals(lancamento.getDataPagamento(), resultDTO.getDataPagamento());
        assertEquals(lancamento.getValor(), resultDTO.getValor());
        assertEquals(lancamento.getObservacao(), resultDTO.getObservacao());
        assertEquals(lancamento.getTipo(), resultDTO.getTipo());
        assertEquals(lancamento.getCategoria().getNome(), resultDTO.getCategoria());
        assertEquals(lancamento.getPessoa().getNome(), resultDTO.getPessoa());
    }

    private void assertInputDTOMapToEntity(LancamentoInputDTO inputDTO, Lancamento lancamento) {
        assertEquals(inputDTO.getDescricao(), lancamento.getDescricao());
        assertEquals(inputDTO.getDataVencimento(), lancamento.getDataVencimento());
        assertEquals(inputDTO.getDataPagamento(), lancamento.getDataPagamento());
        assertEquals(inputDTO.getValor(), lancamento.getValor());
        assertEquals(inputDTO.getObservacao(), lancamento.getObservacao());
        assertEquals(inputDTO.getTipo(), lancamento.getTipo());
        assertEquals(inputDTO.getCategoria().getCodigo(), lancamento.getCategoria().getCodigo());
        assertEquals(inputDTO.getPessoa().getCodigo(), lancamento.getPessoa().getCodigo());
    }
}
