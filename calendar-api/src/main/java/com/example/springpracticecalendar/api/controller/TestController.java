package com.example.springpracticecalendar.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class TestController {
    private final JavaMailSender javaMailSender;

    @GetMapping("/api/mail")
    public @ResponseBody void sendTestMail(){
        javaMailSender.send(mimeMessage -> {
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setTo("email@email.com");
            helper.setSubject("테스트 메일");
            helper.setText("테스트 메일 입니다.");
            }
        );
    }

    @GetMapping("/test/template")
    public ModelAndView testTemplate(){
        final Map<String, Object> props = new HashMap<>();
        props.put("title", "타이틀입니다.");
        props.put("calendar", "sample@gmail.com");
        props.put("period", "언제부터 언제까지");
        props.put("attendees", List.of("test1@mail.io", "test2@mail.io", "test3@mail.io"));
        props.put("acceptUrl", "http://localhost:8080");
        props.put("rejectUrl", "http://localhost:8080");
        return new ModelAndView("engagement-email", props);
    }
}
