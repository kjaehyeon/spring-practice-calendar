package com.example.springpracticecalendar.api.controller;

import com.example.springpracticecalendar.api.dto.LoginReq;
import com.example.springpracticecalendar.api.dto.SignUpReq;
import com.example.springpracticecalendar.api.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final LoginService loginService;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(
            @Valid @RequestBody SignUpReq signUpReq,
            HttpSession httpSession
    ){
        loginService.signUp(signUpReq, httpSession);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Void> signIn(
            @Valid @RequestBody LoginReq loginReq,
            HttpSession httpSession
    ){
        loginService.login(loginReq, httpSession);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-out")
    public ResponseEntity<Void> signOut(
            HttpSession httpSession
    ){
        loginService.logout(httpSession);
        return ResponseEntity.ok().build();
    }
}
