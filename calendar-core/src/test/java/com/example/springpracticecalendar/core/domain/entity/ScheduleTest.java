package com.example.springpracticecalendar.core.domain.entity;

import com.example.springpracticecalendar.core.constant.ScheduleType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootApplication
class ScheduleTest {

    @Test
    void eventCreate(){
        final User me = User.builder()
                .name("name")
                .email("email")
                .password("password")
                .build();
        final Schedule taskSchedule = Schedule.task("할일", "청소하기", LocalDateTime.now(), me);
        System.out.println(taskSchedule);
        assertEquals(taskSchedule.getScheduleType(), ScheduleType.TASK);
        assertEquals(taskSchedule.toTask().getTitle(), "할일");
    }
}