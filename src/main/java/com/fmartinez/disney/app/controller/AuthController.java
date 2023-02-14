package com.fmartinez.disney.app.controller;

import com.fmartinez.disney.app.dto.AuthenticationResponse;
import com.fmartinez.disney.app.dto.LoginRegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping("/api/auth")
public interface AuthController {

    @PostMapping("/register")
    ResponseEntity<AuthenticationResponse> register(@RequestBody LoginRegisterRequest request) throws InstantiationException, IllegalAccessException, IOException;

    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRegisterRequest request);


}
