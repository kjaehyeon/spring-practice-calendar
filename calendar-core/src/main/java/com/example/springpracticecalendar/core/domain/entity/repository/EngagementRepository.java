package com.example.springpracticecalendar.core.domain.entity.repository;

import com.example.springpracticecalendar.core.domain.entity.Engagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngagementRepository extends JpaRepository<Engagement, Long> {
}
