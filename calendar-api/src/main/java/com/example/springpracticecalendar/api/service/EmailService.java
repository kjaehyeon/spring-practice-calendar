package com.example.springpracticecalendar.api.service;

import com.example.springpracticecalendar.api.dto.EngagementEmailStuff;
import com.example.springpracticecalendar.core.domain.entity.Engagement;

public interface EmailService {
    void sendEngagement(EngagementEmailStuff stuff);
}
