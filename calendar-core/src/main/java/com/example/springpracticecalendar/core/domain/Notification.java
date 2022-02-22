package com.example.springpracticecalendar.core.domain;

import com.example.springpracticecalendar.core.domain.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    private Schedule schedule;
}
