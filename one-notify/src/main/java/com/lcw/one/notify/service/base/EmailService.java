package com.lcw.one.notify.service.base;

import com.lcw.one.util.exception.OneBaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.from.mail}")
    private String fromEmail;

    @Value("${mail.from.name}")
    private String fromName;

    public EmailService() {
    }

    public EmailService(final JavaMailSender javaMailSender, final String fromEmail, final String fromName) {
        this.javaMailSender = javaMailSender;
        this.fromEmail = fromEmail;
        this.fromName = fromName;
    }

    public void sendEmail(String email, String title, String content) {
        logger.info("Send Email To:[{}], title:[{}], Content:[{}]", email, title, content);
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
        logger.info("Send Email To:[{}], title:[{}], Content:[{}]", emailList, title, content);
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
