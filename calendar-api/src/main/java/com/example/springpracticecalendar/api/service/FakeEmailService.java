package com.example.springpracticecalendar.api.service;

import com.example.springpracticecalendar.core.domain.entity.Engagement;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("dev")
@Service
public class FakeEmailService implements EmailService{
    @Override
    public void sendEngagement(Engagement engagement) {
        System.out.println("send email \n Email: "
                +engagement.getAttendee().getEmail()
                +", schedule : " + engagement.getSchedule().getId()
        );
    }
}
