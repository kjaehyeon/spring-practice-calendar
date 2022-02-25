package com.example.springpracticecalendar.api.service;

import com.example.springpracticecalendar.api.dto.AuthUser;
import com.example.springpracticecalendar.api.dto.ScheduleDto;
import com.example.springpracticecalendar.api.util.DtoConverter;
import com.example.springpracticecalendar.core.domain.entity.repository.EngagementRepository;
import com.example.springpracticecalendar.core.domain.entity.repository.ScheduleRepository;
import com.example.springpracticecalendar.core.util.Period;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleQueryService {

    private final ScheduleRepository scheduleRepository;
    private final EngagementRepository engagementRepository;

    public List<ScheduleDto> getScheduleByDay(AuthUser authUser, LocalDate date) {
        return getScheduleDtoListByPeriod(authUser, Period.of(date, date));
    }

    public List<ScheduleDto> getScheduleByWeek(AuthUser authUser, LocalDate startOfWeek) {
        return getScheduleDtoListByPeriod(authUser, Period.of(startOfWeek, startOfWeek.plusDays(6)));
    }

    public List<ScheduleDto> getScheduleByMonth(AuthUser authUser, YearMonth yearMonth) {
        return getScheduleDtoListByPeriod(authUser, Period.of(yearMonth.atDay(1), yearMonth.atEndOfMonth()));
    }

    private List<ScheduleDto> getScheduleDtoListByPeriod(AuthUser authUser, Period period) {
        return Stream.concat(
                //이 부분은 쿼리를 최적화해서 DB단에서 해결할 수 있는 로직이다.
                scheduleRepository.findAllByWriter_Id(authUser.getId())
                        .stream()
                        .filter(schedule -> schedule.isOverlapped(period))
                        .map(DtoConverter::fromSchedule)
                , engagementRepository.findAllByAttendee_Id(authUser.getId())
                        .stream()
                        .filter(engagement -> engagement.isOverlapped(period))
                        .map(engagement -> DtoConverter.fromSchedule(engagement.getSchedule()))
        ).collect(Collectors.toList());
    }
}
