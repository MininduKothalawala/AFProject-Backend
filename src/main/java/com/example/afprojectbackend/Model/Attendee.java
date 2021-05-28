package com.example.afprojectbackend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Attendee")
public class Attendee {

    @Id
    private String a_id;
    private String a_name;
    private String a_email;
    private String a_mobileNo;

    public Attendee(String a_id, String a_name, String a_email, String a_mobileNo) {
        this.a_id = a_id;
        this.a_name = a_name;
        this.a_email = a_email;
        this.a_mobileNo = a_mobileNo;
    }

    public String getA_id() {
        return a_id;
    }

    public void setA_id(String a_id) {
        this.a_id = a_id;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_email() {
        return a_email;
    }

    public void setA_email(String a_email) {
        this.a_email = a_email;
    }

    public String getA_mobileNo() {
        return a_mobileNo;
    }

    public void setA_mobileNo(String a_mobileNo) {
        this.a_mobileNo = a_mobileNo;
    }
}
