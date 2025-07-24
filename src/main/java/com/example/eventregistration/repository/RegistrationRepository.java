package com.example.eventregistration.repository;

import com.example.eventregistration.model.Registration;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RegistrationRepository extends MongoRepository<Registration, String> {
    List<Registration> findByEventType(String eventType);
}
