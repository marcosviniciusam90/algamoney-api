package com.algamoney.api.event;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class RecursoCriadoEvent extends ApplicationEvent {

    private Long codigo;
    private final HttpServletResponse response;

    public RecursoCriadoEvent(Object source, Long codigo, HttpServletResponse response) {
        super(source);
        this.codigo = codigo;
        this.response = response;
    }

    public Long getCodigo() {
        return codigo;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
}
