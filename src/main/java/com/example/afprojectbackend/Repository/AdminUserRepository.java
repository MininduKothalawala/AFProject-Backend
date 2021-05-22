package com.example.afprojectbackend.Repository;

import com.example.afprojectbackend.Model.AdminUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface AdminUserRepository extends MongoRepository<AdminUser, String> {

}
