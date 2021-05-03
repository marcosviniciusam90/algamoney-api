package com.algamoney.api.service;

import com.algamoney.api.dto.LancamentoInputDTO;
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
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.algamoney.api.utils.CategoriaUtils.createCategoria;
import static com.algamoney.api.utils.LancamentoUtils.createLancamento;
import static com.algamoney.api.utils.LancamentoUtils.createLancamentoInputDTO;
import static com.algamoney.api.utils.PessoaUtils.createPessoa;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LancamentoServiceTests {

    private static final Faker FAKER = Faker.instance();
    private static final LancamentoMapper LANCAMENTO_MAPPER = LancamentoMapper.INSTANCE;

    @Mock
    private LancamentoRepository lancamentoRepository;

    @Mock
    private PessoaService pessoaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private LancamentoService lancamentoService;

    @Test
    void testCriarComSucesso() {
        LancamentoInputDTO lancamentoInputDTO = createLancamentoInputDTO();
        Lancamento lancamentoInput = criarLancamento(lancamentoInputDTO);

        verify(lancamentoRepository, times(1)).save(lancamentoInput);
    }

    @Test
    void testCriarComPessoaInexistente() {
        LancamentoInputDTO lancamentoInputDTO = createLancamentoInputDTO();
        Long codigoPessoa = lancamentoInputDTO.getPessoa().getCodigo();

        when(pessoaService.findById(codigoPessoa)).thenThrow(PessoaInexistenteException.class);

        assertThrows(PessoaInexistenteException.class, () -> lancamentoService.criar(lancamentoInputDTO));
    }

    @Test
    void testCriarComPessoaInativa() {
        LancamentoInputDTO lancamentoInputDTO = createLancamentoInputDTO();
        Long codigoPessoa = lancamentoInputDTO.getPessoa().getCodigo();

        when(pessoaService.findById(codigoPessoa)).thenReturn(createPessoa(codigoPessoa, false));

        assertThrows(PessoaInativaException.class, () -> lancamentoService.criar(lancamentoInputDTO));
    }

    @Test
    void testCriarComCategoriaInexistente() {
        LancamentoInputDTO lancamentoInputDTO = createLancamentoInputDTO();
        Long codigoPessoa = lancamentoInputDTO.getPessoa().getCodigo();
        Long codigoCategoria = lancamentoInputDTO.getCategoria().getCodigo();

        when(pessoaService.findById(codigoPessoa)).thenReturn(createPessoa(codigoPessoa, true));
        when(categoriaRepository.findById(codigoCategoria)).thenReturn(Optional.empty());

        assertThrows(CategoriaInexistenteException.class, () -> lancamentoService.criar(lancamentoInputDTO));
    }

    @Test
    void testAtualizarComSucesso() {
        Long codigoLancamento = FAKER.number().randomNumber();
        LancamentoInputDTO lancamentoInputDTO = createLancamentoInputDTO();
        Lancamento lancamentoInput = atualizarLancamento(codigoLancamento, lancamentoInputDTO);

        verify(lancamentoRepository, times(1)).save(lancamentoInput);
    }

    @Test
    void testAtualizarLancamentoInexistente() {
        Long codigoLancamento = FAKER.number().randomNumber();
        LancamentoInputDTO lancamentoInputDTO = createLancamentoInputDTO();

        when(lancamentoRepository.findById(codigoLancamento)).thenReturn(Optional.empty());

        assertThrows(LancamentoInexistenteException.class, () -> lancamentoService.atualizar(codigoLancamento, lancamentoInputDTO));

    }

    @Test
    void testBuscarLancamentoInexistente() {
        Long codigoLancamento = FAKER.number().randomNumber();

        when(lancamentoRepository.findById(codigoLancamento)).thenReturn(Optional.empty());

        assertThrows(LancamentoInexistenteException.class, () -> lancamentoService.findDTOById(codigoLancamento));
    }

    private Lancamento criarLancamento(LancamentoInputDTO lancamentoInputDTO) {
        Lancamento lancamentoInput = LANCAMENTO_MAPPER.inputDTOToEntity(lancamentoInputDTO);

        Long codigoPessoa = lancamentoInputDTO.getPessoa().getCodigo();
        Long codigoCategoria = lancamentoInputDTO.getCategoria().getCodigo();

        Pessoa pessoa = createPessoa(codigoPessoa, true);
        Categoria categoria = createCategoria(codigoCategoria);

        lancamentoInput.setPessoa(pessoa);
        lancamentoInput.setCategoria(categoria);

        when(pessoaService.findById(codigoPessoa)).thenReturn(pessoa);
        when(categoriaRepository.findById(codigoCategoria)).thenReturn(Optional.ofNullable(categoria));

        lancamentoService.criar(lancamentoInputDTO);
        return lancamentoInput;
    }

    private Lancamento atualizarLancamento(Long codigoLancamento, LancamentoInputDTO lancamentoInputDTO) {
        Lancamento lancamentoInput = LANCAMENTO_MAPPER.inputDTOToEntity(lancamentoInputDTO);

        Long codigoPessoa = lancamentoInputDTO.getPessoa().getCodigo();
        Long codigoCategoria = lancamentoInputDTO.getCategoria().getCodigo();

        Pessoa pessoa = createPessoa(codigoPessoa, true);
        Categoria categoria = createCategoria(codigoCategoria);

        lancamentoInput.setPessoa(pessoa);
        lancamentoInput.setCategoria(categoria);

        Lancamento lancamentoExistente = createLancamento();
        lancamentoExistente.setCodigo(codigoLancamento);
        lancamentoInput.setCodigo(codigoLancamento);

        when(lancamentoRepository.findById(codigoLancamento)).thenReturn(Optional.of(lancamentoExistente));
        when(pessoaService.findById(codigoPessoa)).thenReturn(pessoa);
        when(categoriaRepository.findById(codigoCategoria)).thenReturn(Optional.ofNullable(categoria));

        lancamentoService.atualizar(codigoLancamento, lancamentoInputDTO);
        return lancamentoInput;
    }
}
