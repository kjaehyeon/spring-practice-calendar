package com.example.springpracticecalendar.core.domain.entity;

import com.example.springpracticecalendar.core.constant.RequestReplyType;
import com.example.springpracticecalendar.core.constant.RequestStatus;
import com.example.springpracticecalendar.core.domain.Event;
import com.example.springpracticecalendar.core.util.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "engagements")
@Entity
public class Engagement extends BaseEntity{

    @JoinColumn(name = "schedule_id")
    @ManyToOne
    private Schedule schedule;

    @JoinColumn(name = "attendee_id")
    @ManyToOne
    private User attendee;

    @Enumerated(value = EnumType.STRING)
    private RequestStatus requestStatus;

    public Event getEvent(){
        return schedule.toEvent();
    }

    public boolean isOverlapped(Period period){
        return this.schedule.isOverlapped(period);
    }

    public Engagement reply(RequestReplyType requestReplyType) {
        switch (requestReplyType){
            case ACCEPT:
                this.requestStatus = RequestStatus.ACCEPTED;
                break;
            case REJECT:
                this.requestStatus = RequestStatus.REJECTED;
                break;
        }
        return this;
    }
}
