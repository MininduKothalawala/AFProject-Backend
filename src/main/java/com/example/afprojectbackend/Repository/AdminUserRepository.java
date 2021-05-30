package com.example.afprojectbackend.Repository;

import com.example.afprojectbackend.Model.AdminUser;
import com.example.afprojectbackend.Model.Conference;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface AdminUserRepository extends MongoRepository<AdminUser, String> {

    @Query("{'name' : ?0}")
    Optional<AdminUser> findByUsername (String name);


}
