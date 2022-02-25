package com.example.springpracticecalendar.api.service;

import com.example.springpracticecalendar.core.domain.entity.Engagement;

public interface EmailService {
    void sendEngagement(Engagement engagement);
}
