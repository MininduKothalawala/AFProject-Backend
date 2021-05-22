package com.example.afprojectbackend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Researcher {

    @Id
    private String R_userId;
    private String R_name;
    private String R_email;
    private String R_mobileNo;

    public Researcher() {
    }

    public Researcher(String r_userId, String r_name, String r_email, String r_mobileNo) {
        R_userId = r_userId;
        R_name = r_name;
        R_email = r_email;
        R_mobileNo = r_mobileNo;
    }

    public String getR_userId() {
        return R_userId;
    }

    public void setR_userId(String r_userId) {
        R_userId = r_userId;
    }

    public String getR_name() {
        return R_name;
    }

    public void setR_name(String r_name) {
        R_name = r_name;
    }

    public String getR_email() {
        return R_email;
    }

    public void setR_email(String r_email) {
        R_email = r_email;
    }

    public String getR_mobileNo() {
        return R_mobileNo;
    }

    public void setR_mobileNo(String r_mobileNo) {
        R_mobileNo = r_mobileNo;
    }
}
