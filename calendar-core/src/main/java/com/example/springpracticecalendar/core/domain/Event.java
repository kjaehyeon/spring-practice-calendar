package com.example.springpracticecalendar.core.domain;

import com.example.springpracticecalendar.core.domain.entity.Schedule;
import com.example.springpracticecalendar.core.util.Period;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Schedule schedule;

    public boolean isOverlapped(LocalDateTime startAt, LocalDateTime endAt) {
        return schedule.isOverlapped(startAt, endAt);
       // return schedule.getStartAt().isBefore(endAt) && startAt.isBefore(schedule.getEndAt());
    }
}
