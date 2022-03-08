package com.example.springpracticecalendar.api.service;

import com.example.springpracticecalendar.api.dto.EngagementEmailStuff;
import com.example.springpracticecalendar.core.domain.entity.Engagement;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("test")
@Service
public class FakeEmailService implements EmailService{
    @Override
    public void sendEngagement(EngagementEmailStuff stuff) {
        System.out.println("send email \n Email: "
                +stuff.getSubject()
        );
    }
}
