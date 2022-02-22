package com.example.springpracticecalendar.core.domain;

import com.example.springpracticecalendar.core.domain.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Schedule schedule;

    public String getTitle(){
        return schedule.getTitle();
    }
}
