package com.algamoney.api.service;

import com.algamoney.api.dto.LancamentoInputDTO;
import com.algamoney.api.dto.LancamentoResultDTO;
import com.algamoney.api.mapper.LancamentoMapper;
import com.algamoney.api.model.Categoria;
import com.algamoney.api.model.Lancamento;
import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.CategoriaRepository;
import com.algamoney.api.repository.LancamentoRepository;
import com.algamoney.api.utils.CategoriaUtils;
import com.algamoney.api.utils.PessoaUtils;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.algamoney.api.utils.LancamentoUtils.createLancamentoInputDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void whenNewBookInformedThenReturnSuccessCreateMessage() {
        LancamentoInputDTO lancamentoInputDTO = createLancamentoInputDTO();
        Lancamento lancamentoInput = lancamentoMapper.inputDTOToEntity(lancamentoInputDTO);

        Long codigoPessoa = lancamentoInput.getPessoa().getCodigo();
        Pessoa pessoa = PessoaUtils.createPessoa(codigoPessoa, true);
        lancamentoInput.setPessoa(pessoa);

        Long codigoCategoria = lancamentoInput.getCategoria().getCodigo();
        Categoria categoria = CategoriaUtils.createCategoria(codigoCategoria);
        lancamentoInput.setCategoria(categoria);

        when(pessoaService.findById(codigoPessoa)).thenReturn(pessoa);
        when(categoriaRepository.findById(codigoCategoria))
                .thenReturn(Optional.of(categoria));

        Gson gson = new Gson();
        Lancamento lancamentoCriado = gson.fromJson(gson.toJson(lancamentoInput), Lancamento.class);
        lancamentoCriado.setCodigo(faker.number().randomNumber());

        when(lancamentoRepository.save(lancamentoInput)).thenReturn(lancamentoCriado);

        LancamentoResultDTO lancamentoResultDTO = lancamentoService.criar(lancamentoInputDTO);
        assertEquals(lancamentoCriado.getCodigo(), lancamentoResultDTO.getCodigo());
    }
}
