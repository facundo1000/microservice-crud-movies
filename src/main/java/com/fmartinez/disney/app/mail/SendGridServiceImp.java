package com.fmartinez.disney.app.mail;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGridAPI;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.fmartinez.disney.app.constant.ApplicationConstant.MAIL_SENDER;
import static com.fmartinez.disney.app.constant.ApplicationConstant.MAIL_SUBJECT;

@Service
@Slf4j
@PropertySource("classpath:application-dev.properties")
public class SendGridServiceImp implements MailService {
    private final SendGridAPI sendGrid;
    private final String templateID;

    public SendGridServiceImp(SendGridAPI sendGrid, @Value("${spring.sendgrid.template.id}") String templateID) {
        this.sendGrid = sendGrid;
        this.templateID = templateID;
    }

    @Override
    public Map<String, String> getMailInfo() {
        Map<String, String> response = new HashMap<>();

        response.put("Host:", sendGrid.getHost());
        response.put("Version:", sendGrid.getVersion());
        response.put("Library Version:", sendGrid.getLibraryVersion());
        return response;
    }


    @Override
    public void sendPlainText(String reciver) throws IOException {
        Mail mail = getMail(reciver);

        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sendGrid.api(request);


        log.info("Mail Plain Text");
        log.info("Mail sent to:" + reciver);
        log.info("Status code: " + response.getStatusCode());
    }

    @Override
    public void sendTemplateMail(String email) throws IOException {
        Email from = new Email(MAIL_SENDER, "Stardust.Cia");
        Email to = new Email(email);
        Mail mail = new Mail();

        Personalization personalization = new Personalization();
        personalization.addTo(to);
        personalization.addDynamicTemplateData("email", email);
        mail.setFrom(from);
        mail.setSubject(MAIL_SUBJECT);
        mail.addPersonalization(personalization);
        mail.setTemplateId(templateID);


        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        Response response = sendGrid.api(request);

        log.info("Mail Tamplate");
        log.info("Mail sent to:" + email);
        log.info("Status code: " + response.getStatusCode());
    }

    private static Mail getMail(String reciver) {
        Email from = new Email(MAIL_SENDER, "Stardust.Cia");
        Email to = new Email(reciver);
        Content content = new Content("text/plain", "SendGrid it's working");
        return new Mail(from, MAIL_SUBJECT, to, content);
    }

}
