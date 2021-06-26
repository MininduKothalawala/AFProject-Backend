package com.example.afprojectbackend.Service;

import com.example.afprojectbackend.Model.Conference;
import com.example.afprojectbackend.Repository.ConferenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceService {

    private final ConferenceRepository conferenceRepository;


    public ConferenceService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    public void addConference(Conference conference){

        conferenceRepository.insert(conference);
    }

    public void updateConference(Conference conference){

        Conference savedConference = conferenceRepository.findById(conference.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Expense by ID %s", conference.getId())));
        savedConference.setConferenceName(conference.getConferenceName());
        savedConference.setConferenceName(conference.getDescription());
        savedConference.setDate(conference.getDate());
        savedConference.setStartingTime(conference.getStartingTime());
        savedConference.setEndingTime(conference.getEndingTime());
        savedConference.setVenue(conference.getVenue());
        savedConference.setStatus(conference.getStatus());


        conferenceRepository.save(conference);
    }

    public List<Conference> getAllConference(){
        return conferenceRepository.findAll();
    }

    public void deleteConference(String id) {

        conferenceRepository.deleteById(id);
    }

    public List<Conference> getConferenceByStatus(String status){
        //return conferenceRepository.findByStatus("Pending");
        return conferenceRepository.findByStatus(status);
    }


    public void AproveConference(Conference conference){

        Conference savedConference = conferenceRepository.findById(conference.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Expense by ID %s", conference.getId())));
        savedConference.setConferenceName(conference.getConferenceName());
        savedConference.setDate(conference.getDate());
        savedConference.setStartingTime(conference.getStartingTime());
        savedConference.setEndingTime(conference.getEndingTime());
        savedConference.setVenue(conference.getVenue());
        savedConference.setStatus("Approved");


        conferenceRepository.save(conference);
    }

    public Object getConferenceById(String id){
        return conferenceRepository.findById(id);

    }


}

