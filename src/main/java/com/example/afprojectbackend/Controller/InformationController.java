package com.example.afprojectbackend.Controller;

import com.example.afprojectbackend.Service.InformationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://af-icaf-frontend.azurewebsites.net")
@RequestMapping("/api/information")
public class InformationController {

    private final InformationService informationService;

    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    @GetMapping("/getAttendeeCount")
    public int getAttendeeCount(){
        return informationService.getAttendeeCount();
    }

    @GetMapping("/getResearcherCount")
    public int getResearcherCount(){
        return informationService.getResearcherCount();
    }

    @GetMapping("/getConductorCount")
    public int getConductorCount(){
        return informationService.getConductorCount();
    }

    @GetMapping("/getAttendeePaymentStatus")
    public int[] getAttendeePaymentStatus(){
        //['pending', 'paid']
        return informationService.getAttendeePaymentStatus();
    }

    @GetMapping("/getResearcherPaymentStatus")
    public int[] getResearcherPaymentStatus(){
        //['pending', 'paid']
        return informationService.getResearcherPaymentStatus();
    }

    @GetMapping("/getResearchStatus")
    public int[] getResearchStatus(){
        //['approve', 'pending', 'reject']
        return informationService.getResearchStatus();
    }

    @GetMapping("/getConferenceStatus")
    public int[] getConferenceStatus(){
        //['approve', 'pending', 'reject', 'update']
        return informationService.getConferenceStatus();
    }
}
