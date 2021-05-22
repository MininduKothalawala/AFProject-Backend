package com.example.afprojectbackend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Attendee {

    @Id
    private String A_id;
    private String A_name;
    private String A_email;
    private String A_mobileNo;

    public Attendee() {
    }

    public Attendee(String a_id, String a_name, String a_email, String a_mobileNo) {
        A_id = a_id;
        A_name = a_name;
        A_email = a_email;
        A_mobileNo = a_mobileNo;
    }

    public String getA_id() {
        return A_id;
    }

    public void setA_id(String a_id) {
        A_id = a_id;
    }

    public String getA_name() {
        return A_name;
    }

    public void setA_name(String a_name) {
        A_name = a_name;
    }

    public String getA_email() {
        return A_email;
    }

    public void setA_email(String a_email) {
        A_email = a_email;
    }

    public String getA_mobileNo() {
        return A_mobileNo;
    }

    public void setA_mobileNo(String a_mobileNo) {
        A_mobileNo = a_mobileNo;
    }
}
