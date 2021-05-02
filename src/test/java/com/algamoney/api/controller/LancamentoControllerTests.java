package com.algamoney.api.controller;

import com.algamoney.api.dto.LancamentoInputDTO;
import com.algamoney.api.dto.LancamentoResultDTO;
import com.algamoney.api.event.RecursoCriadoEvent;
import com.algamoney.api.mapper.LancamentoMapper;
import com.algamoney.api.model.Lancamento;
import com.algamoney.api.repository.LancamentoRepository;
import com.algamoney.api.service.LancamentoService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static com.algamoney.api.utils.JsonUtils.*;
import static com.algamoney.api.utils.LancamentoUtils.createLancamentoInputDTO;
import static com.algamoney.api.utils.LancamentoUtils.createLancamentoResultDTO;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class LancamentoControllerTests {

    private static final LancamentoMapper lancamentoMapper = LancamentoMapper.INSTANCE;

    private static final Faker faker = Faker.instance();

    private static final String API_URL_PATH = "/lancamentos";

    private MockMvc mockMvc;

    @Mock
    private LancamentoService lancamentoService;

    @Mock
    private LancamentoRepository lancamentoRepository;

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private LancamentoController lancamentoController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(lancamentoController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((viewName, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void testCriarLancamentoComSucesso() throws Exception {
        LancamentoInputDTO lancamentoInputDTO = createLancamentoInputDTO();
        Lancamento lancamento = lancamentoMapper.inputDTOToEntity(lancamentoInputDTO);

        LancamentoResultDTO lancamentoResultDTO = lancamentoMapper.entityToResultDTO(lancamento);
        lancamentoResultDTO.setCodigo(faker.number().randomNumber());

        when(lancamentoService.criar(lancamentoInputDTO)).thenReturn(lancamentoResultDTO);

        MvcResult mvcResult = mockMvc.perform(post(API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(lancamentoInputDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.codigo", is(lancamentoResultDTO.getCodigo().intValue())))
                .andReturn();

        LancamentoResultDTO response = asObject(mvcResult, LancamentoResultDTO.class);
        assertEquals(lancamentoResultDTO.getDataVencimento(), response.getDataVencimento());

        verify(lancamentoService, times(1)).criar(lancamentoInputDTO);
        verify(publisher, times(1)).publishEvent(any(RecursoCriadoEvent.class));
    }

    @Test
    void testCriarLancamentoComCampoObrigatorioNaoPreenchido() throws Exception {
        LancamentoInputDTO lancamentoInputDTO = createLancamentoInputDTO();
        lancamentoInputDTO.setDescricao(null);

        mockMvc.perform(post(API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(lancamentoInputDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }

    @Test
    void testCriarLancamentoComCorpoDaRequisicaoInvalido() throws Exception {
        LancamentoInputDTO lancamentoInputDTO = createLancamentoInputDTO();
        lancamentoInputDTO.setDescricao(null);

        String trechoASerRemovido = String.format("\"descricao\":%s", lancamentoInputDTO.getDescricao());

        String corpoInvalido = asJsonString(lancamentoInputDTO).replace(trechoASerRemovido, "");

        mockMvc.perform(post(API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(corpoInvalido))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof HttpMessageNotReadableException));
    }

    @Test
    void testBuscarLancamentoPorCodigo() throws Exception {
        LancamentoResultDTO lancamentoResultDTO = createLancamentoResultDTO();
        Long codigo = lancamentoResultDTO.getCodigo();

        when(lancamentoService.findDTOById(codigo)).thenReturn(lancamentoResultDTO);

        MvcResult mvcResult = mockMvc.perform(get(API_URL_PATH + "/" + codigo)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String expectedResponseBody = asJsonString(lancamentoResultDTO);
        String actualResponseBody = getJson(mvcResult);

        assertEquals(expectedResponseBody, actualResponseBody);
    }

//    @Test
//    void testBuscarLancamentoPorCodigoInexistente() throws Exception {
//        Long codigoInexistente = faker.number().randomNumber();
//
//        when(lancamentoService.findDTOById(codigoInexistente)).thenThrow(LancamentoInexistenteException.class);
//
//        mockMvc.perform(get(API_URL_PATH + "/" + codigoInexistente)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound())
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof LancamentoInexistenteException));
//    }

    @Test
    void testExcluirLancamentoComSucesso() throws Exception {
        Long codigo = faker.number().randomNumber();

        mockMvc.perform(delete(API_URL_PATH + "/" + codigo)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(lancamentoRepository, times(1)).deleteById(codigo);
    }


}