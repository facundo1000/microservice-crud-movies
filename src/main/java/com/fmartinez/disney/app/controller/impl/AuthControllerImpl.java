package com.fmartinez.disney.app.controller.impl;

import com.fmartinez.disney.app.controller.AuthController;
import com.fmartinez.disney.app.dto.AuthenticationResponse;
import com.fmartinez.disney.app.dto.LoginRegisterRequest;
import com.fmartinez.disney.app.mail.MailService;
import com.fmartinez.disney.app.security.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthenticationService service;
    private final MailService mailService;

    @Override
    public ResponseEntity<AuthenticationResponse> register(LoginRegisterRequest email) throws IOException {
        mailService.sendTemplateMail(email.getEmail());
        return ResponseEntity.ok(service.register(email));
    }

    @Override
    public ResponseEntity<AuthenticationResponse> login(LoginRegisterRequest request) {
        return ResponseEntity.ok(service.login(request));
    }
}
