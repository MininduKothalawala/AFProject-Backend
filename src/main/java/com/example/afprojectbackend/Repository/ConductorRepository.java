package com.example.afprojectbackend.Repository;

import com.example.afprojectbackend.Model.Conductor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConductorRepository extends MongoRepository<Conductor, String> {
}
