package com.example.afprojectbackend.Repository;

import com.example.afprojectbackend.Model.Attendee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AttendeeRepository extends MongoRepository<Attendee, String> {
//    List<Attendee> findByA_conferenceId(String conferenceID);
//
//    List<Attendee> findByA_payment_status(String p_status);
}
