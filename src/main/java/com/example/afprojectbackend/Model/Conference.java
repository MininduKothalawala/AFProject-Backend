package com.example.afprojectbackend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Conference")
public class Conference {

    @Id
    private String id;
    private String conferenceName;
    private String description;
    private String startingDate;
    private String endingDate;
    private String addedBy;
    private String venue;
    private String status;
    private String payment;

    public Conference() {
    }

    public Conference(String id, String conferenceName, String description, String startingDate, String endingDate, String addedBy, String venue, String status, String payment) {
        this.id = id;
        this.conferenceName = conferenceName;
        this.description = description;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.addedBy = addedBy;
        this.venue = venue;
        this.status = status;
        this.payment = payment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }
}
