package com.example.springpracticecalendar.api.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class SignUpReq {
    @NotBlank
    private final String name;

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    @Size(min = 6, message = "6자리 이상 입력해 주세요.")
    private final String password;

    @NotNull
    private final LocalDate birthday;
}
