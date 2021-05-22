package com.example.afprojectbackend.Repository;

import com.example.afprojectbackend.Model.Researcher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResearcherRepository extends MongoRepository<Researcher, String> {
}
