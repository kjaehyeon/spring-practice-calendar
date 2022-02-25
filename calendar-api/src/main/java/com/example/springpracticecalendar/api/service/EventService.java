package com.example.springpracticecalendar.api.service;

import com.example.springpracticecalendar.api.dto.AuthUser;
import com.example.springpracticecalendar.api.dto.EventCreateReq;
import com.example.springpracticecalendar.core.domain.entity.Engagement;
import com.example.springpracticecalendar.core.domain.entity.Schedule;
import com.example.springpracticecalendar.core.domain.entity.User;
import com.example.springpracticecalendar.core.domain.entity.repository.EngagementRepository;
import com.example.springpracticecalendar.core.domain.entity.repository.ScheduleRepository;
import com.example.springpracticecalendar.core.exception.CalendarException;
import com.example.springpracticecalendar.core.exception.ErrorCode;
import com.example.springpracticecalendar.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.springpracticecalendar.core.constant.RequestStatus.ACCEPTED;
import static com.example.springpracticecalendar.core.constant.RequestStatus.REQUESTED;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EngagementRepository engagementRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserService userService;
    private final EmailService emailService;

    @Transactional
    public void create(EventCreateReq eventCreateReq, AuthUser authUser) {
        //이벤트 참여자의 다른 이벤트와 중복X
        //회의 시간이 겹치면 안된다.
        //이메일 발송
        final List<Engagement> engagementList = engagementRepository.findAll();
        //TODO findAll 개선

        if(engagementList.stream()
                .anyMatch(e -> eventCreateReq.getAttendeeIds().contains(e.getAttendee().getId())
                    && e.getRequestStatus() == ACCEPTED
                    && e.getEvent().isOverlapped(eventCreateReq.getStartAt(), eventCreateReq.getEndAt())
                )
        ){
            throw new CalendarException(ErrorCode.EVENT_CREATE_OVERLAPPED_PERIOD);
        }

        final Schedule eventSchedule = Schedule.event(
                eventCreateReq.getTitle(),
                eventCreateReq.getDescription(),
                eventCreateReq.getStartAt(),
                eventCreateReq.getEndAt(),
                userService.findById(authUser.getId())
        );
        scheduleRepository.save(eventSchedule);

        eventCreateReq.getAttendeeIds()
                .forEach(Id -> {
                    final User attendee = userService.findById(Id);
                    final Engagement engagement = Engagement.builder()
                            .attendee(attendee)
                            .requestStatus(REQUESTED)
                            .schedule(eventSchedule)
                            .build();

                    engagementRepository.save(engagement);
                    emailService.sendEngagement(engagement);
                });
    }
}
