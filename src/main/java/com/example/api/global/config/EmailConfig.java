package com.example.api.global.config;

import com.example.api.global.properties.EmailProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {
    private final EmailProperties emailProperties;

    public EmailConfig(EmailProperties emailProperties) {
        this.emailProperties = emailProperties;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        System.out.println("Configuring JavaMailSender with host: " + emailProperties.getHost());
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailProperties.getHost());
        mailSender.setPort(emailProperties.getPort());
        mailSender.setUsername(emailProperties.getUsername());
        mailSender.setPassword(emailProperties.getPassword());
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setJavaMailProperties(getMailProperties());

        return mailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", emailProperties.isAuth());
        properties.put("mail.smtp.starttls.enable", emailProperties.isStarttlsEnable());
        properties.put("mail.smtp.starttls.required", emailProperties.isStarttlsRequired());
        properties.put("mail.smtp.connectiontimeout", emailProperties.getConnectionTimeout());
        properties.put("mail.smtp.timeout", emailProperties.getTimeout());
        properties.put("mail.smtp.writetimeout", emailProperties.getWriteTimeout());
        properties.put("mail.smtp.expirationMillis", emailProperties.getExpirationMillis());

        return properties;
    }
}