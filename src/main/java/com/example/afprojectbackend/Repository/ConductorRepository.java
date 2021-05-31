package com.example.afprojectbackend.Repository;

import com.example.afprojectbackend.Model.Conductor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ConductorRepository extends MongoRepository<Conductor, String> {
    List<Conductor> findByC_conferenceId(String conferenceID);

    List<Conductor> findByC_submission_status(String s_status);
}
