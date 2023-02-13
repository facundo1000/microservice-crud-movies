package com.fmartinez.disney.app.controller.impl;

import com.fmartinez.disney.app.controller.SendGridController;
import com.fmartinez.disney.app.mail.MailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class SendGridControllerImpl implements SendGridController {

    private final MailService service;

    @Override
    public ResponseEntity<?> getMailInfo() {
        return new ResponseEntity<>(service.getMailInfo(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> sendText(@PathVariable String email) throws IOException {
        service.sendPlainText(email);
        Map<String, String> response = getResponse(email);

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<?> sendMailTemplate(String email) throws IOException {
        service.sendTemplateMail(email);
        Map<String, String> response = getResponse(email);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    private static Map<String, String> getResponse(String email) {
        Map<String, String> response = new HashMap<>();
        response.put("mensagge", "Email sent");
        response.put("to", email);
        return response;
    }
}
