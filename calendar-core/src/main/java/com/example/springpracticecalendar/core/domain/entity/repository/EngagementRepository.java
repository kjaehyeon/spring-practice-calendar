package com.example.springpracticecalendar.core.domain.entity.repository;

import com.example.springpracticecalendar.core.domain.entity.Engagement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EngagementRepository extends JpaRepository<Engagement, Long> {
    List<Engagement> findAllByAttendee_Id(Long userId);
}
