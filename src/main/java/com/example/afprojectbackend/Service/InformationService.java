package com.example.afprojectbackend.Service;

import com.example.afprojectbackend.Model.Attendee;
import com.example.afprojectbackend.Repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationService {

    private final AdminUserRepository adminUserRepository;
    private final AttendeeRepository attendeeRepository;
    private final ConferenceRepository conferenceRepository;
    private final ConductorRepository conductorRepository;
    private final ResearcherRepository researcherRepository;

    public InformationService(AdminUserRepository adminUserRepository, AttendeeRepository attendeeRepository, ConferenceRepository conferenceRepository, ConductorRepository conductorRepository, ResearcherRepository researcherRepository) {
        this.adminUserRepository = adminUserRepository;
        this.attendeeRepository = attendeeRepository;
        this.conferenceRepository = conferenceRepository;
        this.conductorRepository = conductorRepository;
        this.researcherRepository = researcherRepository;
    }

    //get single values
    public int getAttendeeCount(){
        return  (int) attendeeRepository.count();
    }
    public int getResearcherCount(){
        return (int) researcherRepository.count();
    }
    public int getConductorCount(){
        return (int) conductorRepository.count();
    }

    //get value arrays
//    public int[] getAttendeePaymentStatus(){
//        int [] attendeePaymentStatus = new int[2];
//        List<Attendee> attendees = attendeeRepository.findAll();
//
//        for (Attendee attendee: attendees){
//            if(attendee.)
//        }
//    }
}
