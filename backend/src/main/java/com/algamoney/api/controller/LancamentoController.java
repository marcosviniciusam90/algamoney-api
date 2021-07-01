package com.algamoney.api.controller;

import com.algamoney.api.dto.LancamentoInputDTO;
import com.algamoney.api.dto.LancamentoResponseDTO;
import com.algamoney.api.dto.LancamentoResumoDTO;
import com.algamoney.api.event.RecursoCriadoEvent;
import com.algamoney.api.exceptionhandler.Erro;
import com.algamoney.api.exceptionhandler.utils.ErroUtils;
import com.algamoney.api.model.Lancamento;
import com.algamoney.api.repository.LancamentoRepository;
import com.algamoney.api.repository.filter.LancamentoFilter;
import com.algamoney.api.service.LancamentoService;
import com.algamoney.api.service.exception.CategoriaInexistenteException;
import com.algamoney.api.service.exception.PessoaInativaException;
import com.algamoney.api.service.exception.PessoaInexistenteException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LancamentoController {

    private final LancamentoRepository lancamentoRepository;
    private final LancamentoService lancamentoService;
    private final ApplicationEventPublisher publisher;

    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Lancamento> filtrar (LancamentoFilter lancamentoFilter, Pageable pageable) {
        return lancamentoRepository.filtrar(lancamentoFilter, pageable);
    }

    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    @GetMapping(params = "resumo")
    @ResponseStatus(HttpStatus.OK)
    public Page<LancamentoResumoDTO> resumir (LancamentoFilter lancamentoFilter, Pageable pageable) {
        return lancamentoRepository.resumir(lancamentoFilter, pageable);
    }

    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    @GetMapping("/{codigo}")
    @ResponseStatus(HttpStatus.OK)
    public LancamentoResponseDTO buscarPeloCodigo(@PathVariable Long codigo) {
        return lancamentoService.findDTOById(codigo);
    }

    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LancamentoResponseDTO criar(@Valid @RequestBody LancamentoInputDTO lancamentoInputDTO, HttpServletResponse response) {
        LancamentoResponseDTO lancamentoDTO = lancamentoService.criar(lancamentoInputDTO);
        publisher.publishEvent(new RecursoCriadoEvent(this, lancamentoDTO.getCodigo(), response));
        return lancamentoDTO;
    }

    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
    @PutMapping("/{codigo}")
    public LancamentoResponseDTO atualizar(@PathVariable Long codigo, @Valid @RequestBody LancamentoInputDTO lancamentoInputDTO) {
        return lancamentoService.atualizar(codigo, lancamentoInputDTO);
    }

    @PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasScope('write')")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById (@PathVariable Long codigo) {
        lancamentoRepository.deleteById(codigo);
    }

    @ExceptionHandler(PessoaInexistenteException.class)
    public ResponseEntity<Object> handlePessoaInexistenteException(PessoaInexistenteException ex, WebRequest request) {
        List<Erro> erros = ErroUtils.criarErro("lancamento.pessoa-inexistente", ex);
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(PessoaInativaException.class)
    public ResponseEntity<Object> handlePessoaInativaException(PessoaInativaException ex, WebRequest request) {
        List<Erro> erros = ErroUtils.criarErro("lancamento.pessoa-inativa", ex);
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(CategoriaInexistenteException.class)
    public ResponseEntity<Object> handleCategoriaInexistenteException(CategoriaInexistenteException ex, WebRequest request) {
        List<Erro> erros = ErroUtils.criarErro("lancamento.categoria-inexistente", ex);
        return ResponseEntity.badRequest().body(erros);
    }
}
