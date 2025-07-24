package com.example.eventregistration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

//    @GetMapping("/register")
//    public String showRegisterForm() {
//        return "user/register";
//    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "admin/login";
    }

    @GetMapping("/admin/form")
    public String showAdminForm() {
        return "admin/event-form";
    }

    @GetMapping("/event-form")
    public String showEventForm() {
        return "user/event-form";
    }

    @GetMapping("/admin/dashboard")
    public String showAdminDashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

}

