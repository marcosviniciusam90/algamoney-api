package com.algamoney.api.mapper;

import com.algamoney.api.dto.LancamentoInputDTO;
import com.algamoney.api.dto.LancamentoResumoDTO;
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
    void givenEntityMapToResumoDTO() {
        Lancamento lancamento = createLancamento();
        LancamentoResumoDTO lancamentoResumoDTO = LANCAMENTO_MAPPER.entityToResumoDTO(lancamento);

        assertEntityMapToResumoDTO(lancamento, lancamentoResumoDTO);
    }

    private void assertEntityMapToResumoDTO(Lancamento lancamento, LancamentoResumoDTO lancamentoResumoDTO) {
        assertEquals(lancamento.getCodigo(), lancamentoResumoDTO.getCodigo());
        assertEquals(lancamento.getDescricao(), lancamentoResumoDTO.getDescricao());
        assertEquals(lancamento.getDataVencimento(), lancamentoResumoDTO.getDataVencimento());
        assertEquals(lancamento.getDataPagamento(), lancamentoResumoDTO.getDataPagamento());
        assertEquals(lancamento.getValor(), lancamentoResumoDTO.getValor());
        assertEquals(lancamento.getObservacao(), lancamentoResumoDTO.getObservacao());
        assertEquals(lancamento.getTipo(), lancamentoResumoDTO.getTipo());
        assertEquals(lancamento.getCategoria().getNome(), lancamentoResumoDTO.getCategoria());
        assertEquals(lancamento.getPessoa().getNome(), lancamentoResumoDTO.getPessoa());
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
