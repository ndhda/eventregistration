package com.example.eventregistration.controller;

import com.example.eventregistration.model.Event;
import com.example.eventregistration.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    // ✅ Get all available event types (for user to select)
    @GetMapping
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // ✅ Get event questions by event type
    @GetMapping("/{type}")
    public Event getEventByType(@PathVariable String type) {
        return eventRepository.findByType(type);
    }

    // ✅ Admin creates event type with questions
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    // ✅ Admin adds a question to an existing event type (by ID)
    @PatchMapping("/{id}/questions")
    public Event addQuestionToEvent(@PathVariable String id, @RequestBody Event.Question question) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event not found"));
        event.getQuestions().add(question);
        return eventRepository.save(event);
    }
}
