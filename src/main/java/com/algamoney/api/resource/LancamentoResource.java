package com.algamoney.api.resource;

import com.algamoney.api.dto.LancamentoInputDTO;
import com.algamoney.api.dto.LancamentoResultDTO;
import com.algamoney.api.event.RecursoCriadoEvent;
import com.algamoney.api.exceptionhandler.Erro;
import com.algamoney.api.model.Lancamento;
import com.algamoney.api.repository.LancamentoRepository;
import com.algamoney.api.repository.filter.LancamentoFilter;
import com.algamoney.api.service.LancamentoService;
import com.algamoney.api.service.exception.CategoriaInexistenteException;
import com.algamoney.api.service.exception.PessoaInexistenteOuInativaException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private MessageSource messageSource;

    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    @GetMapping
    public Page<Lancamento> filtrar (LancamentoFilter lancamentoFilter, Pageable pageable) {
        return lancamentoRepository.filtrar(lancamentoFilter, pageable);
    }

    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    @GetMapping(params = "resumo")
    public Page<LancamentoResultDTO> filtrarResumir (LancamentoFilter lancamentoFilter, Pageable pageable) {
        return lancamentoRepository.filtrarResumir(lancamentoFilter, pageable);
    }

    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    @GetMapping("/{codigo}")
    public ResponseEntity<LancamentoResultDTO> buscar(@PathVariable Long codigo) {
        Optional<Lancamento> lancamentoExistente = lancamentoRepository.findById(codigo);

        if(!lancamentoExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        LancamentoResultDTO result = modelMapper.map(lancamentoExistente.get(), LancamentoResultDTO.class);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
    @PostMapping
    public ResponseEntity<LancamentoResultDTO> criar(@Valid @RequestBody LancamentoInputDTO lancamentoInputDTO, HttpServletResponse response) {
        LancamentoResultDTO result = lancamentoService.salvar(lancamentoInputDTO);
        publisher.publishEvent(new RecursoCriadoEvent(this, result.getCodigo(), response));
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
    @PutMapping("/{codigo}")
    public ResponseEntity<LancamentoResultDTO> atualizar(@PathVariable Long codigo, @Valid @RequestBody LancamentoInputDTO lancamentoInputDTO) {
        LancamentoResultDTO result = lancamentoService.atualizar(codigo, lancamentoInputDTO);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasScope('write')")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir (@PathVariable Long codigo) {
        lancamentoRepository.deleteById(codigo);
    }

    @ExceptionHandler(PessoaInexistenteOuInativaException.class)
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
        List<Erro> erro = criaErro(ex, "pessoa.inexistente-ou-inativa");
        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(CategoriaInexistenteException.class)
    public ResponseEntity<Object> handleCategoriaInexistenteException(CategoriaInexistenteException ex) {
        List<Erro> erro = criaErro(ex, "categoria.inexistente");
        return ResponseEntity.badRequest().body(erro);
    }

    private List<Erro> criaErro(Exception ex, String identificador) {
        String mensagemUsuario = messageSource.getMessage(identificador, null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        return Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
    }
}
