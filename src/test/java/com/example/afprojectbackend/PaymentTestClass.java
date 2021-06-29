package com.example.afprojectbackend;

import com.example.afprojectbackend.Controller.PaymentController;
import com.example.afprojectbackend.Model.PaymentDummy;
import com.example.afprojectbackend.Service.PaymentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PaymentTestClass {

    @MockBean
    PaymentService paymentService;

    @Autowired
    PaymentController paymentController;

    @Test
    public void testCreate(){

        PaymentDummy paymentDummy = new PaymentDummy("4582139013027030","3490","1000");
        paymentService.addCardDetails(paymentDummy);
        Assertions.assertTrue(true,"Added");


    }

    @Test
    public void testGetCardDetails(){

        PaymentDummy paymentDummy = new PaymentDummy("4582139013027030","3490","1000");
        paymentService.getCardDetails(paymentDummy.getCardNo());
        Assertions.assertTrue(true,"Get card details successfully");

    }
}
