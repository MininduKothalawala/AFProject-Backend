package com.example.afprojectbackend.Repository;

import com.example.afprojectbackend.Model.Attendee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AttendeeRepository extends MongoRepository<Attendee, String> {
}
