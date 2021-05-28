package com.example.afprojectbackend.Model;

import com.mongodb.internal.connection.Time;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document("conference")
public class Conference {

    @Id
    private String id;

    private String conferenceName;

    private String date;

    private String startingTime;

    private String endingTime;

    private String venue;

    private String status;


    public Conference(String id, String conferenceName, String date, String startingTime, String endingTime, String venue, String status) {
        this.id = id;
        this.conferenceName = conferenceName;
        this.date = date;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.venue = venue;
        this.status = status;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
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
}
