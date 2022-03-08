package com.example.springpracticecalendar.api.controller;

import com.example.springpracticecalendar.api.service.LoginService;
import com.example.springpracticecalendar.core.constant.RequestReplyType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/events/engagements/{engagementId}")
    public String updateEngagement(
            @PathVariable Long engagementId,
            @RequestParam RequestReplyType type,
            Model model,
            HttpSession httpSession
    ){
        model.addAttribute("isSignIn", httpSession.getAttribute(LoginService.LOGIN_SESSION_KEY));
        model.addAttribute("updateType", type.name());
        model.addAttribute("engagementId", engagementId);
        model.addAttribute("path", "/events/engagements/"+engagementId+"?type="+type.name());
        return "update-event";
    }
}
