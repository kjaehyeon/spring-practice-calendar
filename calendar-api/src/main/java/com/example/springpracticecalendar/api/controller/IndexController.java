package com.example.springpracticecalendar.api.controller;

import com.example.springpracticecalendar.api.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(
            Model model,
            HttpSession httpSession,
            @RequestParam(required = false) String redirect
    ){
        model.addAttribute("isSignIn", httpSession.getAttribute(LoginService.LOGIN_SESSION_KEY) != null);
        model.addAttribute("redirect", redirect);
        return "index";
    }
}
