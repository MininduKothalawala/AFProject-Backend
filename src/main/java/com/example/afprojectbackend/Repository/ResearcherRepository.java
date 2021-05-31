package com.example.afprojectbackend.Repository;

import com.example.afprojectbackend.Model.Researcher;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ResearcherRepository extends MongoRepository<Researcher, String> {
    List<Researcher> findByR_conferenceId(String conferenceID);

    List<Researcher> findByR_pay_status(String p_status);

    List<Researcher> findByR_submission_status(String s_status);
}
