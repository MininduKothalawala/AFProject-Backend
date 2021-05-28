package com.example.afprojectbackend.Controller;

import com.example.afprojectbackend.Model.PaymentDummy;
import com.example.afprojectbackend.Repository.PaymentRepository;
import com.example.afprojectbackend.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/addcarddetails")
    public ResponseEntity addCardDetails(@RequestBody PaymentDummy paymentDummy){
        paymentService.addCardDetails(paymentDummy);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getcarddetail/{cardNo}")
    public Object getCardDetails(@PathVariable String cardNo){
        return ResponseEntity.ok(paymentService.getCardDetails(cardNo));
    }
}
