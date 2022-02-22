package com.example.springpracticecalendar.core.domain.entity;

import com.example.springpracticecalendar.core.constant.ScheduleType;
import com.example.springpracticecalendar.core.domain.Event;
import com.example.springpracticecalendar.core.domain.Notification;
import com.example.springpracticecalendar.core.domain.Task;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class Schedule extends BaseEntity{

    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String title;
    private String description;

    @JoinColumn(name = "writer_id")
    @ManyToOne
    private User writer;


    @Enumerated(EnumType.STRING)
    private ScheduleType scheduleType;

    public static Schedule event(
            String title,
            String description,
            LocalDateTime startAt,
            LocalDateTime endAt,
            User writer){
        return Schedule.builder()
                .title(title)
                .description(description)
                .startAt(startAt)
                .endAt(endAt)
                .writer(writer)
                .scheduleType(ScheduleType.EVENT)
                .build();
    }
    public static Schedule task(
            String title,
            String description,
            LocalDateTime taskAt,
            User writer
    ){
        return Schedule.builder()
                .title(title)
                .description(description)
                .startAt(taskAt)
                .writer(writer)
                .scheduleType(ScheduleType.TASK)
                .build();
    }
    public static Schedule notification(
            String title,
            LocalDateTime notifyAt,
            User writer
    ){
        return Schedule.builder()
                .title(title)
                .startAt(notifyAt)
                .writer(writer)
                .scheduleType(ScheduleType.NOTIFICATION)
                .build();
    }

    public Task toTask(){
        return new Task(this);
    }
    public Event toEvent(){
        return new Event(this);
    }
    public Notification toNotification(){
        return new Notification(this);
    }

}