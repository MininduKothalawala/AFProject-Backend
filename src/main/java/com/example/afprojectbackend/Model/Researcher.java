package com.example.afprojectbackend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Researcher")
public class Researcher {

    @Id
    private String r_id;
    private String r_name;
    private String r_email;
    private String r_mobileNo;

    public Researcher(String r_id, String r_name, String r_email, String r_mobileNo) {
        this.r_id = r_id;
        this.r_name = r_name;
        this.r_email = r_email;
        this.r_mobileNo = r_mobileNo;
    }

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getR_email() {
        return r_email;
    }

    public void setR_email(String r_email) {
        this.r_email = r_email;
    }

    public String getR_mobileNo() {
        return r_mobileNo;
    }

    public void setR_mobileNo(String r_mobileNo) {
        this.r_mobileNo = r_mobileNo;
    }
}
