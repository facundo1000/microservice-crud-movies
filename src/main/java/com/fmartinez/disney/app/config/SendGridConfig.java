package com.fmartinez.disney.app.config;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-dev.properties")
public class SendGridConfig {
    private final String key;

    public SendGridConfig(@Value("${spring.sendgrid.api-key}") String key) {
        this.key = key;
    }

    @Bean
    public SendGrid getSendGrid() {
        return new SendGrid(key);
    }
}

