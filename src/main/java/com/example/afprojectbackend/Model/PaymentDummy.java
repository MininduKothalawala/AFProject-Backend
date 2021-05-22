package com.example.afprojectbackend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("PaymentDummy")
public class PaymentDummy {

    @Id
    private String cardNo;
    private String pinNo;
    private String amount;

    public PaymentDummy() {
    }

    public PaymentDummy(String cardNo, String pinNo, String amount) {
        this.cardNo = cardNo;
        this.pinNo = pinNo;
        this.amount = amount;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPinNo() {
        return pinNo;
    }

    public void setPinNo(String pinNo) {
        this.pinNo = pinNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
