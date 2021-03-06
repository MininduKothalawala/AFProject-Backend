package com.example.afprojectbackend.Service;

import com.example.afprojectbackend.Model.Conference;
import com.example.afprojectbackend.Repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceService {

    private final ConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    public void addConference(Conference conference) {

        conferenceRepository.insert(conference);
    }

    public void updateConference(Conference conference) {

        Conference savedConference = conferenceRepository.findById(conference.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Expense by ID %s", conference.getId())));
        savedConference.setConferenceName(conference.getConferenceName());
        savedConference.setStartingDate(conference.getStartingDate());
        savedConference.setEndingDate(conference.getEndingDate());
        savedConference.setConferenceName(conference.getDescription());
        savedConference.setVenue(conference.getVenue());
        savedConference.setStatus(conference.getStatus());

        conferenceRepository.save(conference);

    }

    public List<Conference> getAllConference() {
        return conferenceRepository.findAll();
    }

    public void deleteConference(String id) {

        conferenceRepository.deleteById(id);
    }

    public List<Conference> getConferenceByStatus(String status) {
        return conferenceRepository.findByStatus(status);
    }


//    public void ApproveConference(Conference conference) {
//
//        Conference savedConference = conferenceRepository.findById(conference.getId())
//                .orElseThrow(() -> new RuntimeException(
//                        String.format("Cannot Find Expense by ID %s", conference.getId())));
//        savedConference.setConferenceName(conference.getConferenceName());
//        savedConference.setStartingDate(conference.getStartingDate());
//        savedConference.setEndingDate(conference.getEndingDate());
//        savedConference.setVenue(conference.getVenue());
//        savedConference.setStatus("Approved");
//
//
//        conferenceRepository.save(conference);
//    }

    public Object getConferenceById(String id) {
        return conferenceRepository.findById(id);
    }

    public void changeStatus(String id, String status) {

        Conference conference = conferenceRepository.findConferenceById(id);
        conference.setStatus(status);

        conferenceRepository.save(conference);
    }

}

