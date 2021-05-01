package com.algamoney.api.exceptionhandler;

import com.algamoney.api.exceptionhandler.utils.ErroUtils;
import com.algamoney.api.service.exception.LancamentoInexistenteException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    /**
     * Tratamento de exceção quando o corpo da requisição está mal formado
     * Ex: falta caracter, virgula, etc
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Erro> erros = ErroUtils.criarErro("mensagem.invalida", ex);
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Tratamento de exceção quando um ou mais parâmetros do corpo da requisição falham nas validações
     * (@NotNull, @NotBlank, @Size, etc)
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Erro> erros = ErroUtils.criarListaDeErrosDosCampos(ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Tratamento de exceção quando tenta deletar um recurso que não existe
     * @see org.springframework.data.jpa.repository.support.SimpleJpaRepository#deleteById(Object) 
     */
    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        List<Erro> erros = ErroUtils.criarErro("recurso.nao-encontrado", ex);
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    /**
     * Tratamento de exceção quando tenta, por exemplo, inserir um lançamento associado a uma categoria inexistente,
     * o que resultaria em erro de FK
     */
    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        List<Erro> erros = ErroUtils.criarErro("recurso.operacao-nao-permitida", ex);
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(LancamentoInexistenteException.class)
    public ResponseEntity<Object> handleLancamentoInexistenteException(LancamentoInexistenteException ex, WebRequest request) {
        List<Erro> erros = ErroUtils.criarErro("lancamento.inexistente", ex);
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


}
