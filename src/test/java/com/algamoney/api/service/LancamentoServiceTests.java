package com.algamoney.api.service;

import com.algamoney.api.dto.LancamentoInputDTO;
import com.algamoney.api.dto.LancamentoResultDTO;
import com.algamoney.api.mapper.LancamentoMapper;
import com.algamoney.api.model.Categoria;
import com.algamoney.api.model.Lancamento;
import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.CategoriaRepository;
import com.algamoney.api.repository.LancamentoRepository;
import com.algamoney.api.service.exception.CategoriaInexistenteException;
import com.algamoney.api.service.exception.LancamentoInexistenteException;
import com.algamoney.api.service.exception.PessoaInativaException;
import com.algamoney.api.service.exception.PessoaInexistenteException;
import com.algamoney.api.utils.BeanUtils;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.algamoney.api.utils.CategoriaUtils.createCategoria;
import static com.algamoney.api.utils.LancamentoUtils.createLancamentoInputDTO;
import static com.algamoney.api.utils.PessoaUtils.createPessoa;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LancamentoServiceTests {

    private static final Faker faker = Faker.instance();
    private static final LancamentoMapper lancamentoMapper = LancamentoMapper.INSTANCE;

    @Mock
    private LancamentoRepository lancamentoRepository;

    @Mock
    private PessoaService pessoaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private LancamentoService lancamentoService;

    @Test
    void dadoNovoLancamentoDeveRetornarResultado() {
        LancamentoInputDTO lancamentoInputDTO = createLancamentoInputDTO();
        Lancamento lancamentoInput = lancamentoMapper.inputDTOToEntity(lancamentoInputDTO);

        Long codigoPessoa = lancamentoInput.getPessoa().getCodigo();
        Pessoa pessoa = createAndSetPerson(lancamentoInput, codigoPessoa);

        Long codigoCategoria = lancamentoInput.getCategoria().getCodigo();
        Categoria categoria = createAndSetCategory(lancamentoInput, codigoCategoria);

        when(pessoaService.findById(codigoPessoa)).thenReturn(pessoa);
        when(categoriaRepository.findById(codigoCategoria)).thenReturn(Optional.of(categoria));

        Lancamento lancamentoCriado = BeanUtils.clone(lancamentoInput);
        lancamentoCriado.setCodigo(faker.number().randomNumber());

        when(lancamentoRepository.save(lancamentoInput)).thenReturn(lancamentoCriado);

        LancamentoResultDTO lancamentoResultDTO = lancamentoService.criar(lancamentoInputDTO);
        assertEquals(lancamentoCriado.getCodigo(), lancamentoResultDTO.getCodigo());
    }

    @Test
    void dadoNovoLancamentoComPessoaInexistenteDeveRetornarExcecao() throws PessoaInexistenteException {
        LancamentoInputDTO lancamentoInputDTO = createLancamentoInputDTO();
        Long codigoPessoa = lancamentoInputDTO.getPessoa().getCodigo();

        when(pessoaService.findById(codigoPessoa)).thenThrow(PessoaInexistenteException.class);

        assertThrows(PessoaInexistenteException.class, () -> lancamentoService.criar(lancamentoInputDTO));
    }

    @Test
    void dadoNovoLancamentoComPessoaInativaDeveRetornarExcecao() throws PessoaInexistenteException {
        LancamentoInputDTO lancamentoInputDTO = createLancamentoInputDTO();
        Long codigoPessoa = lancamentoInputDTO.getPessoa().getCodigo();

        when(pessoaService.findById(codigoPessoa)).thenReturn(createPessoa(codigoPessoa, false));

        assertThrows(PessoaInativaException.class, () -> lancamentoService.criar(lancamentoInputDTO));
    }

    @Test
    void dadoNovoLancamentoComCategoriaInexistenteDeveRetornarExcecao() throws PessoaInexistenteException {
        LancamentoInputDTO lancamentoInputDTO = createLancamentoInputDTO();
        Long codigoPessoa = lancamentoInputDTO.getPessoa().getCodigo();
        Long codigoCategoria = lancamentoInputDTO.getCategoria().getCodigo();

        when(pessoaService.findById(codigoPessoa)).thenReturn(createPessoa(codigoPessoa, true));
        when(categoriaRepository.findById(codigoCategoria)).thenReturn(Optional.empty());

        assertThrows(CategoriaInexistenteException.class, () -> lancamentoService.criar(lancamentoInputDTO));
    }

    @Test
    void quandoNaoEncontrarLancamentoPeloCodigoDeveRetornarExcecao() {
        Long codigoLancamento = faker.number().randomNumber();

        when(lancamentoRepository.findById(codigoLancamento)).thenReturn(Optional.empty());

        assertThrows(LancamentoInexistenteException.class, () -> lancamentoService.findDTOById(codigoLancamento));
    }

    private Categoria createAndSetCategory(Lancamento lancamentoInput, Long codigoCategoria) {
        Categoria categoria = createCategoria(codigoCategoria);
        lancamentoInput.setCategoria(categoria);
        return categoria;
    }

    private Pessoa createAndSetPerson(Lancamento lancamentoInput, Long codigoPessoa) {
        Pessoa pessoa = createPessoa(codigoPessoa, true);
        lancamentoInput.setPessoa(pessoa);
        return pessoa;
    }
}
