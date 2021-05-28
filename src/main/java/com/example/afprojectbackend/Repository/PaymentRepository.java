package com.example.afprojectbackend.Repository;

import com.example.afprojectbackend.Model.PaymentDummy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<PaymentDummy, String> {
}
