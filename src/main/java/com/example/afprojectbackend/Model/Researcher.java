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
    private String r_filename;
    private String r_fileId;
    private String r_conferenceId;
    private String r_pay_status;

    public Researcher() {
    }

    public Researcher(String r_id, String r_name, String r_email, String r_mobileNo, String r_filename, String r_fileId, String r_conferenceId, String r_pay_status) {
        this.r_id = r_id;
        this.r_name = r_name;
        this.r_email = r_email;
        this.r_mobileNo = r_mobileNo;
        this.r_filename = r_filename;
        this.r_fileId = r_fileId;
        this.r_conferenceId = r_conferenceId;
        this.r_pay_status = r_pay_status;
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

    public String getR_filename() {
        return r_filename;
    }

    public void setR_filename(String r_filename) {
        this.r_filename = r_filename;
    }

    public String getR_fileId() {
        return r_fileId;
    }

    public void setR_fileId(String r_fileId) {
        this.r_fileId = r_fileId;
    }

    public String getR_conferenceId() {
        return r_conferenceId;
    }

    public void setR_conferenceId(String r_conferenceId) {
        this.r_conferenceId = r_conferenceId;
    }

    public String getR_pay_status() {
        return r_pay_status;
    }

    public void setR_pay_status(String r_pay_status) {
        this.r_pay_status = r_pay_status;
    }
}
