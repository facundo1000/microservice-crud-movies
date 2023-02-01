package com.fmartinez.disney.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

public interface SendGridController {

    @GetMapping("/email/info")
    ResponseEntity<?> getMailInfo();

    @PostMapping("/email/{email}")
    ResponseEntity<String> sendText(@PathVariable String email) throws IOException;
}
