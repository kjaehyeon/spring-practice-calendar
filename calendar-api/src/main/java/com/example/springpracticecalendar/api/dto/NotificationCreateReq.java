package com.example.springpracticecalendar.api.dto;

import com.example.springpracticecalendar.core.constant.TimeUnit;
import com.example.springpracticecalendar.core.exception.CalendarException;
import com.example.springpracticecalendar.core.exception.ErrorCode;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class NotificationCreateReq {
    @NotNull
    private final String title;
    @NotNull
    private final LocalDateTime notifyAt;
    private final RepeatInfo repeatInfo;

    public List<LocalDateTime> getRepeatTimes() {
        if(repeatInfo == null){
            return Collections.singletonList(notifyAt);
        }
        return IntStream.range(0, repeatInfo.times)
                .mapToObj(i -> {
                    long increment = (long) repeatInfo.interval.intervalValue * i;

                    switch (repeatInfo.interval.timeUnit){
                        case DAY:
                            return notifyAt.plusDays(increment);
                        case WEEK:
                            return notifyAt.plusWeeks(increment);
                        case MONTH:
                            return notifyAt.plusMonths(increment);
                        case YEAR:
                            return notifyAt.plusYears(increment);
                        default:
                            throw new CalendarException(ErrorCode.BAD_REQUEST);
                    }
                }).collect(Collectors.toList());
    }
    @Data
    public static class RepeatInfo{
        private final Interval interval;
        private final Integer times;
    }
    @Data
    public static class Interval{
        private final Integer intervalValue;
        private final TimeUnit timeUnit;
    }
}
