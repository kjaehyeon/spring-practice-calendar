package com.example.springpracticecalendar.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginReq {
    private final String email;

    @NotBlank
    @Size(min = 6, message = "6자리 이상 입력해 주세요.")
    private final String password;
}
