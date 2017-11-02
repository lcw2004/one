package com.lcw.one.notify.utils;

import com.lcw.one.util.exception.OneBaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

public class EmailSender {

    private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);
    private JavaMailSender javaMailSender;
    private String fromEmail;
    private String fromName;

    public EmailSender(final JavaMailSender javaMailSender, final String fromEmail, final String fromName) {
        this.javaMailSender = javaMailSender;
        this.fromEmail = fromEmail;
        this.fromName = fromName;
    }

    public void sendEmail(String email, String title, String content) {
        logger.info("To:[{}], title:[{}], Content:[{}]", email, title, content);
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            mimeMessage.addHeader("Content-Type", "text/html;charset=UTF-8");

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(new InternetAddress(fromEmail, fromName, "UTF-8"));
            helper.setTo(email);
            helper.setSubject(title);
            helper.setText(content, true);

            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new OneBaseException("邮件发送失败，异常：" + e.getMessage());
        }
    }

    public void sendEmail(List<String> emailList, String title, String content) {
        logger.info("To:[{}], title:[{}], Content:[{}]", emailList, title, content);
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            mimeMessage.addHeader("Content-Type", "text/html;charset=UTF-8");

            for (String email : emailList) {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                helper.setFrom(new InternetAddress(fromEmail, fromName, "UTF-8"));
                helper.setTo(email);
                helper.setSubject(title);
                helper.setText(content, true);

                javaMailSender.send(mimeMessage);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new OneBaseException("邮件发送失败，异常：" + e.getMessage());
        }
    }
}
