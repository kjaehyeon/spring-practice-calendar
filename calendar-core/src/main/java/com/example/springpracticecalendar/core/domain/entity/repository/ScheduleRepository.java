package com.example.springpracticecalendar.core.domain.entity.repository;

import com.example.springpracticecalendar.core.domain.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByWriter_Id(Long userId);
}
