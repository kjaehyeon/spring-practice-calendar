package com.example.springpracticecalendar.api.service;

import com.example.springpracticecalendar.api.dto.AuthUser;
import com.example.springpracticecalendar.api.dto.TaskCreateReq;
import com.example.springpracticecalendar.core.domain.entity.Schedule;
import com.example.springpracticecalendar.core.domain.entity.repository.ScheduleRepository;
import com.example.springpracticecalendar.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final ScheduleRepository scheduleRepository;
    private final UserService userService;

    public void create(TaskCreateReq taskCreateReq, AuthUser authUser) {
        final Schedule taskSchedule =
                Schedule.task(
                        taskCreateReq.getTitle(),
                        taskCreateReq.getDescription(),
                        taskCreateReq.getTaskAt(),
                        userService.findById(authUser.getId())
                );
        scheduleRepository.save(taskSchedule);
    }
}
