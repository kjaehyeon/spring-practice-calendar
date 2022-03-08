package com.example.springpracticecalendar.api.service;

import com.example.springpracticecalendar.api.dto.AuthUser;
import com.example.springpracticecalendar.api.dto.ReplyEngagementReq;
import com.example.springpracticecalendar.core.constant.RequestReplyType;
import com.example.springpracticecalendar.core.constant.RequestStatus;
import com.example.springpracticecalendar.core.domain.entity.repository.EngagementRepository;
import com.example.springpracticecalendar.core.exception.CalendarException;
import com.example.springpracticecalendar.core.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EngagementService {

    private final EngagementRepository engagementRepository;

    @Transactional
    public RequestStatus update(AuthUser authUser, Long engagementId, RequestReplyType requestReplyType) {
        return engagementRepository.findById(engagementId)
                .filter(e -> e.getRequestStatus() == RequestStatus.REQUESTED)
                .filter(e -> e.getAttendee().getId().equals(authUser.getId()))
                .map(e -> e.reply(requestReplyType))
                .orElseThrow(() -> new CalendarException(ErrorCode.BAD_REQUEST))
                .getRequestStatus();
    }
}
