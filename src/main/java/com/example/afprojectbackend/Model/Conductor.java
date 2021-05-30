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
    private String c_filename;
    private String c_fileId;

    public Conductor() {
    }

    public Conductor(String c_id, String c_name, String c_email, String c_mobileNo, String c_filename, String c_fileId) {
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_email = c_email;
        this.c_mobileNo = c_mobileNo;
        this.c_filename = c_filename;
        this.c_fileId = c_fileId;
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

    public String getC_filename() {
        return c_filename;
    }

    public void setC_filename(String c_filename) {
        this.c_filename = c_filename;
    }

    public String getC_fileId() {
        return c_fileId;
    }

    public void setC_fileId(String c_fileId) {
        this.c_fileId = c_fileId;
    }
}
