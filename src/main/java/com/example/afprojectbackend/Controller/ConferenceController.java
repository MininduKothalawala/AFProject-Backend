package com.example.afprojectbackend.Controller;

import com.example.afprojectbackend.Model.Conference;
import com.example.afprojectbackend.Repository.ConferenceRepository;
import com.example.afprojectbackend.Service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/conference")
public class ConferenceController {

    private final ConferenceService conferenceService;
    @Autowired
    private ConferenceRepository conferenceRepository;


    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;

    }


        @PostMapping
        public ResponseEntity addConference(@RequestBody Conference conference){

            conferenceService.addConference(conference);
            return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping
    public ResponseEntity<List<Conference>> updateConference(@RequestBody Conference conference){
        conferenceService.updateConference(conference);
        return ResponseEntity.ok(conferenceService.getAllConference());
    }

    @GetMapping
    public ResponseEntity<List<Conference>> getAllConference(){
        return ResponseEntity.ok(conferenceService.getAllConference());
    }

    @GetMapping("/{status}")
    public ResponseEntity<List<Conference>> getConferenceByStatus(@PathVariable String status){
        //return ResponseEntity.ok(conferenceService.getConferenceByStatus("Pending"));
        return ResponseEntity.ok(conferenceService.getConferenceByStatus(status));


    }


}
