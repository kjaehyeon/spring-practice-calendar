package com.example.springpracticecalendar.api.dto;

import com.example.springpracticecalendar.core.constant.ScheduleType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationDto implements ScheduleDto{

    private final Long scheduleId;
    private final LocalDateTime notifyAt;
    private final String title;
    private final Long writerId;

    @Override
    public ScheduleType getScheduleType() {
        return ScheduleType.NOTIFICATION;
    }
}
