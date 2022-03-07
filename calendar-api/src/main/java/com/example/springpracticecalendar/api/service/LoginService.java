package com.example.springpracticecalendar.api.service;

import com.example.springpracticecalendar.api.dto.LoginReq;
import com.example.springpracticecalendar.api.dto.SignUpReq;
import com.example.springpracticecalendar.core.domain.entity.User;
import com.example.springpracticecalendar.core.dto.UserCreateReq;
import com.example.springpracticecalendar.core.exception.CalendarException;
import com.example.springpracticecalendar.core.exception.ErrorCode;
import com.example.springpracticecalendar.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    public final static String LOGIN_SESSION_KEY = "USER_ID";
    private final UserService userService;

    public void signUp(
            SignUpReq signUpReq,
            HttpSession session){
        final User user = userService.create(new UserCreateReq(
                signUpReq.getName(),
                signUpReq.getEmail(),
                signUpReq.getPassword(),
                signUpReq.getBirthday()
        ));
        log.info("msg");
        session.setAttribute(LOGIN_SESSION_KEY, user.getId());
    }

    public void login(
            LoginReq loginReq,
            HttpSession session
    ){
        final Long userId = (Long)session.getAttribute(LOGIN_SESSION_KEY);
        if(userId != null)
            return;
        final Optional<User> user =
                userService.findPwMatchUser(loginReq.getEmail(), loginReq.getPassword());

        if(user.isPresent()){
            session.setAttribute(LOGIN_SESSION_KEY, user.get().getId());
        }else{
            throw new CalendarException(ErrorCode.PASSWORD_NOT_MATCH);
        }
    }
    public void logout(HttpSession session){
        session.removeAttribute(LOGIN_SESSION_KEY);
    }
}
