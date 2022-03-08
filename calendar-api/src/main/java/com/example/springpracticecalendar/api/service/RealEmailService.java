package com.example.springpracticecalendar.api.service;

import com.example.springpracticecalendar.api.dto.EngagementEmailStuff;
import com.example.springpracticecalendar.core.domain.entity.Engagement;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Locale;

@Service
@Profile("dev")
@RequiredArgsConstructor
public class RealEmailService implements EmailService{
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Override
    public void sendEngagement(EngagementEmailStuff stuff) {
        javaMailSender.send(mimeMessage -> {
                    final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
                    helper.setTo(stuff.getToEmail());
                    helper.setSubject(stuff.getSubject());
                    helper.setText(
                            templateEngine.process("engagement-email",
                                    new Context(Locale.KOREAN, stuff.getProps())),
                            true
                    );
                }
        );
    }
}
