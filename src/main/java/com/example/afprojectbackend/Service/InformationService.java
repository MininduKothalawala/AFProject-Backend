package com.example.afprojectbackend.Service;

import com.example.afprojectbackend.Model.Attendee;
import com.example.afprojectbackend.Model.Conductor;
import com.example.afprojectbackend.Model.Conference;
import com.example.afprojectbackend.Model.Researcher;
import com.example.afprojectbackend.Repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    public int[] getAttendeePaymentStatus(){
        int [] attendeePaymentStatus = new int[2];
        int pending=0, paid=0;
        List<Attendee> attendees = attendeeRepository.findAll();

        for (Attendee attendee: attendees){
            if(attendee.getA_payment_status().toString().toLowerCase(Locale.ROOT).equals("pending")){
                pending = pending+1;
            }
            else if (attendee.getA_payment_status().toString().toLowerCase(Locale.ROOT).equals("paid")) {
                paid = paid + 1;
            }
        }
        attendeePaymentStatus[0] = pending;
        attendeePaymentStatus[1] = paid;

        //['pending', 'paid']
        return attendeePaymentStatus;
    }

    public int[] getResearcherPaymentStatus(){
        int [] researcherPaymentStatus = new int[2];
        int pending=0, paid=0;
        List<Researcher> researchers = researcherRepository.findAll();

        for (Researcher researcher: researchers){
            if(researcher.getR_pay_status().toString().toLowerCase(Locale.ROOT).equals("pending")){
                pending = pending+1;
            }
            else if (researcher.getR_pay_status().toString().toLowerCase(Locale.ROOT).equals("paid")) {
                paid = paid + 1;
            }
        }
        researcherPaymentStatus[0] = pending;
        researcherPaymentStatus[1] = paid;

        //['pending', 'paid']
        return researcherPaymentStatus;
    }

    public int[] getResearchStatus(){
        int [] researchApproveStatus = new int[3];
        int pending=0, approve=0, reject=0;
        List<Researcher> researchers = researcherRepository.findAll();

        for (Researcher researcher: researchers){
            if(researcher.getR_submission_status().toString().toLowerCase(Locale.ROOT).equals("pending")){
                pending = pending+1;
            }
            else if (researcher.getR_submission_status().toString().toLowerCase(Locale.ROOT).equals("approved")) {
                approve = approve+1;
            }
            else if (researcher.getR_submission_status().toString().toLowerCase(Locale.ROOT).equals("rejected")) {
                reject = reject+1;
            }
        }

        researchApproveStatus[0] = approve;
        researchApproveStatus[1] = pending;
        researchApproveStatus[2] = reject;

        //['approve', 'pending', 'reject']
        return researchApproveStatus;
    }

    public int[] getConferenceStatus(){
        int [] conferenceApproveStatus = new int[4];
        int pending=0, approve=0, reject=0, update=0;
        List<Conference> conferences = conferenceRepository.findAll();

        for (Conference conference: conferences){
            if (conference.getStatus().toString().toLowerCase(Locale.ROOT).equals("pending")){
                pending = pending+1;
            }
            else if (conference.getStatus().toString().toLowerCase(Locale.ROOT).equals("approved")){
                approve = approve+1;
            }
            else if (conference.getStatus().toString().toLowerCase(Locale.ROOT).equals("rejected")){
                reject = reject+1;
            }
            else if (conference.getStatus().toString().toLowerCase(Locale.ROOT).equals("updated")){
                update = update+1;
            }
        }

        conferenceApproveStatus[0] = approve;
        conferenceApproveStatus[1] = pending;
        conferenceApproveStatus[2] = reject;
        conferenceApproveStatus[3] = update;

        //['approve', 'pending', 'reject', 'update']
        return conferenceApproveStatus;
    }

    //get email address by conference id for all users
    public List<String> getEmailAddressesByConference(String conferenceId){
        List<Attendee> attendees = attendeeRepository.findAll();
        List<Researcher> researchers = researcherRepository.findAll();
        List<Conductor> conductors = conductorRepository.findAll();

        List<String> emailList = new ArrayList<>();

        for (Attendee attendee : attendees){
            if (attendee.getA_conferenceId().toString().toLowerCase(Locale.ROOT).equals(conferenceId.toLowerCase(Locale.ROOT))){
                emailList.add(attendee.getA_email());
            }
        }
        for (Researcher researcher: researchers){
            if (researcher.getR_conferenceId().toString().toLowerCase(Locale.ROOT).equals(conferenceId.toLowerCase(Locale.ROOT))){
                emailList.add(researcher.getR_email());
            }
        }
        for (Conductor conductor: conductors){
            if (conductor.getC_conferenceId().toString().toLowerCase(Locale.ROOT).equals(conferenceId.toLowerCase(Locale.ROOT))){
                emailList.add(conductor.getC_email());
            }
        }

        return emailList;
    }
}
