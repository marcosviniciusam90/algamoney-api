package com.algamoney.api.resource;

import com.algamoney.api.event.RecursoCriadoEvent;
import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.PessoaRepository;
import com.algamoney.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ApplicationEventPublisher publisher;

    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
    @GetMapping
    public Page<Pessoa> pesquisar(@RequestParam(required = false, defaultValue = "") String nome, Pageable pageable) {
        return pessoaRepository.findAllByNomeContaining(nome, pageable);
    }

    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
    @GetMapping("/{codigo}")
    public ResponseEntity<Pessoa> buscar(@PathVariable Long codigo) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(codigo);
        return pessoa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
    @PostMapping
    public ResponseEntity<Pessoa> criar (@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        publisher.publishEvent(new RecursoCriadoEvent(this, pessoaSalva.getCodigo(), response));

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasScope('write')")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir (@PathVariable Long codigo) {
        pessoaRepository.deleteById(codigo);
    }

    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
    @PutMapping("/{codigo}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
        Pessoa pessoaAtualizada = pessoaService.atualizar(codigo, pessoa);
        return ResponseEntity.ok(pessoaAtualizada);
    }

    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
    @PutMapping("/{codigo}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
        pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
    }


}
