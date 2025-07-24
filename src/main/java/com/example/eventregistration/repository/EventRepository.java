package com.example.eventregistration.repository;

import com.example.eventregistration.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
    Event findByType(String type);
}
