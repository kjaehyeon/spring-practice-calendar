package com.example.springpracticecalendar.core.domain.entity.repository;

import com.example.springpracticecalendar.core.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
