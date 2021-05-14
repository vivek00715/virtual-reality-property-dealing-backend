package com.hashedin.virtualproperty.application.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private Configuration freemarkerConfig;

    private final Logger logger = LoggerFactory.getLogger(MailService.class);

    public void sendVerificationEmail(String to, String link) throws IOException, MessagingException, TemplateException {
        this.sendEmail(to, link, "Verify your email at Hash Homes", "email_verify.ftl");
        this.logger.info("VERIFICATION EMAIL SEND TO " + to);
    }

    public void sendPasswordResetEmail(String to, String link) throws IOException, MessagingException, TemplateException {
        this.sendEmail(to, link, "Password reset requested at Hash Homes", "email_reset.ftl");
        this.logger.info("PASSWORD RESET EMAIL SEND TO " + to);
    }

    private void sendEmail(String to, String link, String subject, String templateName) throws MessagingException, IOException, TemplateException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Template t = freemarkerConfig.getTemplate(templateName);
        Map model = new HashMap();
        model.put("link", link);
        model.put("email", to);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        helper.setTo(to);
        helper.setText(html, true);
        helper.setSubject(subject);
        helper.setFrom("hashhomeshu@gmail.com");

        emailSender.send(message);
    }
}