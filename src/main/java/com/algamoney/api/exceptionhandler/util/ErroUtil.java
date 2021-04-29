package com.algamoney.api.exceptionhandler.util;

import com.algamoney.api.exceptionhandler.Erro;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ErroUtil {

    @Autowired
    private MessageSource messageSource;

    private static MessageSource sMessageSource;

    @PostConstruct
    public void init() {
        ErroUtil.sMessageSource = messageSource;
    }

    public static List<Erro> criarErro(String identificadorMensagem, Exception ex) {
        String mensagemUsuario = sMessageSource.getMessage(identificadorMensagem, null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
        return Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
    }

    public static List<Erro> criarListaDeErrosDosCampos(BindingResult bindingResult) {
        List<Erro> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String mensagemUsuario = sMessageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            String mensagemDesenvolvedor = fieldError.toString();
            erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        }

        return erros;
    }
}
