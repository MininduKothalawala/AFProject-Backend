package com.example.afprojectbackend.Repository;

import com.example.afprojectbackend.Model.Template;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemplateRepository extends MongoRepository<Template, String> {
}
