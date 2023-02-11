package com.fmartinez.disney.app.mail;

import java.io.IOException;
import java.util.Map;

public interface MailService {
    Map<String, String> getMailInfo();

    String sendPlainText(String reciver) throws IOException;
}
