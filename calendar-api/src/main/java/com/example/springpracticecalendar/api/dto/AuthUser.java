package com.example.springpracticecalendar.api.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthUser {
    private final Long id;

    public static AuthUser of(Long id){
        return new AuthUser(id);
    }
}
