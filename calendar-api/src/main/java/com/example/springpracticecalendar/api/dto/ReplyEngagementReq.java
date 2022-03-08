package com.example.springpracticecalendar.api.dto;

import com.example.springpracticecalendar.core.constant.RequestReplyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyEngagementReq {
    private RequestReplyType type; //REJECT
}
