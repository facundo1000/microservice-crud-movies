package com.fmartinez.disney.app.mail;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGridAPI;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.fmartinez.disney.app.constant.ApplicationConstant.MAIL_SENDER;
import static com.fmartinez.disney.app.constant.ApplicationConstant.MAIL_SUBJECT;

@Service
@Slf4j
public class SendGridService {
    private final SendGridAPI sendGrid;

    public SendGridService(SendGridAPI sendGrid) {
        this.sendGrid = sendGrid;
    }

    public Map<String, String> getMailInfo() {
        Map<String, String> response = new HashMap<>();

        response.put("Host:", sendGrid.getHost());
        response.put("Version:", sendGrid.getVersion());
        response.put("Library Version:", sendGrid.getLibraryVersion());
        return response;
    }


    public String sendPlainText(String reciver) throws IOException {
        Mail mail = getMail(reciver);

        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sendGrid.api(request);

        log.info("Status code: " + response.getStatusCode());
        log.info(response.getBody());
        return request.getBody();

    }

    private static Mail getMail(String reciver) {
        Email from = new Email(MAIL_SENDER, "Stardust.Cia");
        Email to = new Email(reciver);
        Content content = new Content("text/plain", "SendGrid it's working");
        return new Mail(from, MAIL_SUBJECT, to, content);
    }

}
