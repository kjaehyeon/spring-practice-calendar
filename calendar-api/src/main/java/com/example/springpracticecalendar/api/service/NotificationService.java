package com.example.springpracticecalendar.api.service;

import com.example.springpracticecalendar.api.dto.AuthUser;
import com.example.springpracticecalendar.api.dto.NotificationCreateReq;
import com.example.springpracticecalendar.core.domain.entity.Schedule;
import com.example.springpracticecalendar.core.domain.entity.User;
import com.example.springpracticecalendar.core.domain.entity.repository.ScheduleRepository;
import com.example.springpracticecalendar.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final UserService userService;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void create(NotificationCreateReq notificationCreateReq, AuthUser authUser) {
        final User user = userService.findById(authUser.getId());
        final List<LocalDateTime> notifyAtList = notificationCreateReq.getRepeatTimes();

        notifyAtList.forEach(notifyAt -> {
            scheduleRepository.save(Schedule.notification(
                    notificationCreateReq.getTitle(),
                    notifyAt,
                    user
                    )
                );
            }
        );
    }
}
