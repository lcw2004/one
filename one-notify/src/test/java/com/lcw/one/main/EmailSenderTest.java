package com.lcw.one.main;

import com.lcw.one.notify.utils.EmailSender;
import com.sun.mail.util.MailSSLSocketFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.*;

public class EmailSenderTest {

    private JavaMailSender javaMailSender;
    private String fromEmail;
    private String fromName;

    @Before
    public void before() {
        javaMailSender = javaMailSender();
        fromEmail = "292232611@qq.com";
        fromName = "长沙瑞财科技";
    }

    @Test
    public void sendEmail() {
        EmailSender emailSender = new EmailSender(javaMailSender, fromEmail, fromName);
        emailSender.sendEmail("292232611@qq.com", "邮件标题", "邮件内容");
    }

    @Test
    public void sendEmailList() {
        List<String> emailList = Arrays.asList(new String[]{"lcw2004@163.com", "292232611@qq.com"});
        EmailSender emailSender = new EmailSender(javaMailSender, fromEmail, fromName);
        emailSender.sendEmail(emailList, "邮件标题", "邮件内容");
    }


    private static JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", "true");
        mailProperties.put("mail.smtp.starttls.enable", "true");
        mailProperties.put("mail.smtp.starttls.required", "true");
        mailProperties.put("mail.smtp.debug", "true");
        mailProperties.put("mail.smtp.socketFactory.port", "465");
        mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        mailProperties.put("mail.smtp.socketFactory.fallback", "false");

        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost("smtp.qq.com");
        mailSender.setPort(Integer.valueOf("465"));
        mailSender.setProtocol("smtp");
        mailSender.setUsername("292232611@qq.com");
        mailSender.setPassword("ezkbxarmubuqbigc");
        return mailSender;
    }
}
