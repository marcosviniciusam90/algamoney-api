package com.algamoney.api.service;

import com.algamoney.api.dto.LancamentoInputDTO;
import com.algamoney.api.dto.LancamentoResumoDTO;
import com.algamoney.api.mapper.LancamentoMapper;
import com.algamoney.api.model.Categoria;
import com.algamoney.api.model.Lancamento;
import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.CategoriaRepository;
import com.algamoney.api.repository.LancamentoRepository;
import com.algamoney.api.service.exception.CategoriaInexistenteException;
import com.algamoney.api.service.exception.LancamentoInexistenteException;
import com.algamoney.api.service.exception.PessoaInativaException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LancamentoService {

    private static final LancamentoMapper LANCAMENTO_MAPPER = LancamentoMapper.INSTANCE;
    private final LancamentoRepository lancamentoRepository;
    private final CategoriaRepository categoriaRepository;
    private final PessoaService pessoaService;

    public LancamentoResumoDTO atualizar(Long codigo, LancamentoInputDTO lancamentoInputDTO) {

        Lancamento lancamentoExistente = findById(codigo);
        Lancamento lancamentoInput = LANCAMENTO_MAPPER.inputDTOToEntity(lancamentoInputDTO);

        verifyAndSetPersonIfValid(lancamentoInput);
        verifyAndSetCategoryIfExist(lancamentoInput);

        lancamentoInput.setCodigo(lancamentoExistente.getCodigo());

        Lancamento lancamentoAtualizado = lancamentoRepository.save(lancamentoInput);
        return LANCAMENTO_MAPPER.entityToResumoDTO(lancamentoAtualizado);

    }

    public LancamentoResumoDTO criar(LancamentoInputDTO lancamentoInputDTO) {

        Lancamento lancamentoInput = LANCAMENTO_MAPPER.inputDTOToEntity(lancamentoInputDTO);

        verifyAndSetPersonIfValid(lancamentoInput);
        verifyAndSetCategoryIfExist(lancamentoInput);

        Lancamento lancamentoCriado = lancamentoRepository.save(lancamentoInput);
        return LANCAMENTO_MAPPER.entityToResumoDTO(lancamentoCriado);

    }

    private void verifyAndSetPersonIfValid(Lancamento lancamento) {
        Pessoa pessoa = pessoaService.findById(lancamento.getPessoa().getCodigo());

        if(pessoa.isInativa()) {
            throw new PessoaInativaException(pessoa.getCodigo());
        }
        lancamento.setPessoa(pessoa);
    }

    private void verifyAndSetCategoryIfExist(Lancamento lancamento) {
        Long categoriaCodigo = lancamento.getCategoria().getCodigo();

        Categoria categoria = categoriaRepository.findById(categoriaCodigo)
                .orElseThrow(() -> new CategoriaInexistenteException(categoriaCodigo));

        lancamento.setCategoria(categoria);
    }

    public LancamentoResumoDTO findDTOById(Long codigo) {
        Lancamento lancamento = findById(codigo);
        return LANCAMENTO_MAPPER.entityToResumoDTO(lancamento);
    }

    public Lancamento findById(Long codigo) {
        return lancamentoRepository.findById(codigo)
                .orElseThrow(() -> new LancamentoInexistenteException(codigo));
    }

}
