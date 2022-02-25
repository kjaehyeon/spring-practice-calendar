package com.example.springpracticecalendar.api.util;

import com.example.springpracticecalendar.api.dto.EventDto;
import com.example.springpracticecalendar.api.dto.NotificationDto;
import com.example.springpracticecalendar.api.dto.ScheduleDto;
import com.example.springpracticecalendar.api.dto.TaskDto;
import com.example.springpracticecalendar.core.domain.entity.Schedule;
import com.example.springpracticecalendar.core.exception.CalendarException;
import com.example.springpracticecalendar.core.exception.ErrorCode;

public abstract class DtoConverter {
    public static ScheduleDto fromSchedule(Schedule schedule){
        switch (schedule.getScheduleType()){
            case EVENT:
                return EventDto.builder()
                        .scheduleId(schedule.getId())
                        .description(schedule.getDescription())
                        .startAt(schedule.getStartAt())
                        .endAt(schedule.getEndAt())
                        .title(schedule.getTitle())
                        .writerId(schedule.getWriter().getId())
                        .build();
            case TASK:
                return TaskDto.builder()
                        .scheduleId(schedule.getId())
                        .description(schedule.getDescription())
                        .taskAt(schedule.getStartAt())
                        .title(schedule.getTitle())
                        .writerId(schedule.getWriter().getId())
                        .build();
            case NOTIFICATION:
                return NotificationDto.builder()
                        .scheduleId(schedule.getId())
                        .notifyAt(schedule.getStartAt())
                        .title(schedule.getTitle())
                        .writerId(schedule.getWriter().getId())
                        .build();
            default:
                throw new CalendarException(ErrorCode.BAD_REQUEST);
        }
    }
}
