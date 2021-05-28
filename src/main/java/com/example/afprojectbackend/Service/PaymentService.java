package com.example.afprojectbackend.Service;

import com.example.afprojectbackend.Model.PaymentDummy;
import com.example.afprojectbackend.Repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void addCardDetails(PaymentDummy paymentDummy){
        paymentRepository.insert(paymentDummy);
    }

    public Optional<PaymentDummy> getCardDetails(String cardNo){
        return paymentRepository.findById(cardNo);
    }
}
