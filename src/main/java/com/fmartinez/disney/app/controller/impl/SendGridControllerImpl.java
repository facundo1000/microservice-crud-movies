package com.fmartinez.disney.app.controller.impl;

import com.fmartinez.disney.app.controller.SendGridController;
import com.fmartinez.disney.app.mail.MailService;
import com.fmartinez.disney.app.mail.SendGridServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class SendGridControllerImpl implements SendGridController {

    private final MailService service;

    @Override
    public ResponseEntity<?> getMailInfo() {
        return new ResponseEntity<>(service.getMailInfo(), HttpStatus.OK);
    }

    @PostMapping("/email/{email}")
    public ResponseEntity<String> sendText(@PathVariable String email) throws IOException {
        return new ResponseEntity<>(service.sendPlainText(email), HttpStatus.OK);
    }
}
