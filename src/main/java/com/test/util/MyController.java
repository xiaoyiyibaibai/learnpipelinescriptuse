package com.test.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@ResponseBody
@RestController
public class MyController {
    @Autowired
    private JavaMailSender mailSender;
    @RequestMapping(method = RequestMethod.GET,value = "/sendMail")
    public void sendMail() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true,"utf-8");
        message.setSubject("測試信息");
        message.setFrom("a250604@sina.com");
        message.setTo("xiaodonghong@gsafety.com");
        String mes = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>我是測試信息</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "sosoososooooddoodododoodododod\n" +
                "</body>\n" +
                "</html>";
        message.setText(mes,true);

        mailSender.send(mimeMessage);

    }
}
