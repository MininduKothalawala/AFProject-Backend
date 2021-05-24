package com.example.afprojectbackend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Conductor")
public class Conductor {

    @Id
    private String c_id;
    private String c_name;
    private String c_email;
    private String c_mobileNo;

    public Conductor(String c_id, String c_name, String c_email, String c_mobileNo) {
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_email = c_email;
        this.c_mobileNo = c_mobileNo;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_email() {
        return c_email;
    }

    public void setC_email(String c_email) {
        this.c_email = c_email;
    }

    public String getC_mobileNo() {
        return c_mobileNo;
    }

    public void setC_mobileNo(String c_mobileNo) {
        this.c_mobileNo = c_mobileNo;
    }
}
