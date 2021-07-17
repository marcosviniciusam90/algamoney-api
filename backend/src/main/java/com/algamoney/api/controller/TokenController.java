package com.algamoney.api.controller;

import com.algamoney.api.config.property.AlgamoneyApiProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    @Autowired
    private AlgamoneyApiProperty algamoneyApiProperty;

    @DeleteMapping("/revoke")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void revoke(HttpServletRequest request, HttpServletResponse response) {

        boolean isEnableHttps = algamoneyApiProperty.getSeguranca().isEnableHttps();

        ResponseCookie cookie = ResponseCookie.from("refreshToken", null)
                .httpOnly(true)		// proíbe JavaScript de ler
                .secure(isEnableHttps)		// se true, só irá aceitar HTTPS
                .path(request.getContextPath() + "/oauth/token")
                .maxAge(0)
                .sameSite(isEnableHttps ? "None": "Lax")
                .build();

        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
