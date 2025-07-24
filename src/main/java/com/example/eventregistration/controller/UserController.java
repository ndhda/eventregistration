package com.example.eventregistration.controller;

import com.example.eventregistration.model.User;
import com.example.eventregistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        System.out.println("Registration attempt for email: " + user.getEmail());

        Optional<User> existing = userRepository.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            model.addAttribute("error", "Email already registered");
            return "user/register"; // stay on same page and show error
        }

        user.setRole("user");
        userRepository.save(user);

        // Redirect to event form page
        return "redirect:/event-form";
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return userRepository.findAll().stream().map(user -> {
            user.setPassword(null);
            return user;
        }).collect(Collectors.toList());
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }
}
