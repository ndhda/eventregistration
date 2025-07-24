package com.example.eventregistration.controller;

import com.example.eventregistration.model.User;
import com.example.eventregistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody User requestUser) {
        System.out.println("Login attempt for email: " + requestUser.getEmail());

        Optional<User> optionalUser = userRepository.findByEmail(requestUser.getEmail());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User dbUser = optionalUser.get();

        if (!dbUser.getPassword().equals(requestUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }

        if (!"admin".equalsIgnoreCase(dbUser.getRole())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: not admin");
        }

        Map<String, String> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("role", dbUser.getRole());
        response.put("username", dbUser.getUsername());

        return ResponseEntity.ok(response);
    }
}
