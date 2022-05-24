package com.schoolit.schoolit.services.emailsender;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

import javax.mail.internet.MimeMessage;

@Service
public class EmailSenderService implements IEmailSenderService{
    private final static Logger logger = LoggerFactory
            .getLogger(IEmailSenderService.class);
    private final JavaMailSender mailSender;

    @Autowired
    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper((mimeMessage), "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Merci de confirmer votre email");
            helper.setFrom("welcome@schoolit.com");
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            logger.error("failed to send email ", e);
            throw new IllegalStateException("failed to send email");
        }
    }
}
