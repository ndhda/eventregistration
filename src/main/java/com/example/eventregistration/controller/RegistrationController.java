package com.example.eventregistration.controller;

import com.example.eventregistration.model.Registration;
import com.example.eventregistration.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationRepository registrationRepository;

    // ✅ User submits registration form
    @PostMapping
    public ResponseEntity<?> submitRegistration(@RequestBody Registration registration) {
        if (registration.getUsername() == null || registration.getEmail() == null ||
                registration.getEventType() == null || registration.getEventName() == null ||
                registration.getAnswers() == null || registration.getAnswers().isEmpty()) {
            return ResponseEntity.badRequest().body("Missing required fields or answers");
        }

        Registration saved = registrationRepository.save(registration);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // ✅ Admin: View all registered users
    @GetMapping
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    // ✅ Optional: Filter by event type
    @GetMapping("/type/{eventType}")
    public List<Registration> getByEventType(@PathVariable String eventType) {
        return registrationRepository.findByEventType(eventType);
    }

    // ✅ Optional: Filter by user email
    @GetMapping("/email/{email}")
    public List<Registration> getByUserEmail(@PathVariable String email) {
        return registrationRepository.findAll().stream()
                .filter(r -> r.getEmail().equalsIgnoreCase(email))
                .toList();
    }
}
