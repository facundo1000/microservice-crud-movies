package com.fmartinez.disney.app.mail;

import java.io.IOException;
import java.util.Map;

public interface MailService {
    Map<String, String> getMailInfo();

    void sendPlainText(String reciver) throws IOException;

    void sendTemplateMail(String email) throws IOException;
}
