package com.fmartinez.disney.app.controller.impl;

import com.fmartinez.disney.app.controller.AuthController;
import com.fmartinez.disney.app.dto.AuthenticationResponse;
import com.fmartinez.disney.app.dto.LoginRegisterRequest;
import com.fmartinez.disney.app.security.auth.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthController {

    private final AuthenticationService service;

    public AuthControllerImpl(AuthenticationService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<AuthenticationResponse> register(LoginRegisterRequest request) throws InstantiationException, IllegalAccessException {
        return ResponseEntity.ok(service.register(request));
    }

    @Override
    public ResponseEntity<AuthenticationResponse> login(LoginRegisterRequest request) {
        return ResponseEntity.ok(service.login(request));
    }
}
