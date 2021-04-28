package com.algamoney.api.repository.lancamento;

import com.algamoney.api.dto.LancamentoResultDTO;
import com.algamoney.api.model.Lancamento;
import com.algamoney.api.repository.filter.LancamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryQuery {

    Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
    Page<LancamentoResultDTO> filtrarResumir(LancamentoFilter lancamentoFilter, Pageable pageable);
}
