package com.example.afprojectbackend.Repository;

import com.example.afprojectbackend.Model.Conference;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ConferenceRepository extends MongoRepository<Conference, String> {

    @Query("{'name' : ?0}")
    Optional<Conference> findByName (String name);
}
