package com.example.springpracticecalendar.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    private final JavaMailSender javaMailSender;

    @GetMapping("/api/mail")
    public void sendTestMail(){
        javaMailSender.send(mimeMessage -> {
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setTo("email@email.com");
            helper.setSubject("테스트 메일");
            helper.setText("테스트 메일 입니다.");
            }
        );
    }
}
