package com.example.springpracticecalendar.api.dto;

import com.example.springpracticecalendar.core.constant.ScheduleType;
import lombok.Builder;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@Builder
public class EventDto implements ScheduleDto{

    private final Long scheduleId;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;
    private final String title;
    private final String description;
    private final Long writerId;

    @Override
    public ScheduleType getScheduleType() {
        return ScheduleType.EVENT;
    }
}
