package com.example.afprojectbackend.Repository;

import com.example.afprojectbackend.Model.Template;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TemplateRepository extends MongoRepository<Template, String> {
    List<Template> findByTempType(String type);

    @Query("{'username' : { $regex : ?0 , $options: 'i' }}")
    List<Template> findByUsernameContains(String username);

    Template findTemplateById(String id);
}
