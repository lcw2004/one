package com.lcw.one.notify.config;

import com.lcw.one.notify.utils.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class NotifyConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(NotifyConfiguration.class);

    @Autowired
    private Environment environment;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", environment.getProperty("mail.smtp.auth"));
        mailProperties.put("mail.smtp.starttls.enable", environment.getProperty("mail.smtp.starttls.enable"));
        mailProperties.put("mail.smtp.starttls.required", environment.getProperty("mail.smtp.starttls.required"));
        mailProperties.put("mail.smtp.debug", environment.getProperty("mail.smtp.debug"));
        mailProperties.put("mail.smtp.socketFactory.port", environment.getProperty("mail.smtp.socketFactory.port"));
        mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailProperties.put("mail.smtp.socketFactory.fallback", environment.getProperty("mail.smtp.socketFactory.fallback"));

        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(environment.getProperty("mail.host"));
        mailSender.setPort(Integer.valueOf(environment.getProperty("mail.port")));
        mailSender.setProtocol(environment.getProperty("mail.protocol"));
        mailSender.setUsername(environment.getProperty("mail.username"));
        mailSender.setPassword(environment.getProperty("mail.password"));
        return mailSender;
    }

    @Bean
    public EmailSender emailSender(@Value("${mail.from.mail}") String fromEmail,
                                   @Value("${mail.from.name}") String fromName) {
        return new EmailSender(javaMailSender(), fromEmail, fromName);
    }
}
