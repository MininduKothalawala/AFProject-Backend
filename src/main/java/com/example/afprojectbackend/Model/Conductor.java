package com.example.afprojectbackend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Conductor")
public class Conductor {

    @Id
    private String C_id;
    private String C_name;
    private String C_email;
    private String C_mobileNo;

    public Conductor() {
    }

    public Conductor(String c_id, String c_name, String c_email, String c_mobileNo) {
        C_id = c_id;
        C_name = c_name;
        C_email = c_email;
        C_mobileNo = c_mobileNo;
    }

    public String getC_id() {
        return C_id;
    }

    public void setC_id(String c_id) {
        C_id = c_id;
    }

    public String getC_name() {
        return C_name;
    }

    public void setC_name(String c_name) {
        C_name = c_name;
    }

    public String getC_email() {
        return C_email;
    }

    public void setC_email(String c_email) {
        C_email = c_email;
    }

    public String getC_mobileNo() {
        return C_mobileNo;
    }

    public void setC_mobileNo(String c_mobileNo) {
        C_mobileNo = c_mobileNo;
    }
}
