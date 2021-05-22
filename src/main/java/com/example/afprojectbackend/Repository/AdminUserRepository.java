package com.example.afprojectbackend.Repository;

import com.example.afprojectbackend.Model.AdminUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminUserRepository extends MongoRepository<AdminUser, String> {

}
