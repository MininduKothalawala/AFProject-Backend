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
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/conference")
public class ConferenceController {

    private final ConferenceService conferenceService;
    @Autowired
    private ConferenceRepository conferenceRepository;

    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;

    }


     @PostMapping("/addConference")
     public ResponseEntity addConference(@RequestBody Conference conference){

            conferenceService.addConference(conference);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/updateConference/{id}")
    public ResponseEntity<List<Conference>> updateConference(@RequestBody Conference conference){
        conferenceService.updateConference(conference);
        return ResponseEntity.ok(conferenceService.getAllConference());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Conference>> getAllConference(){
        return ResponseEntity.ok(conferenceService.getAllConference());
    }

    @GetMapping("pendingConference/{status}")
    public ResponseEntity<List<Conference>> getPendingConference(@PathVariable String status){

        return ResponseEntity.ok(conferenceService.getConferenceByStatus("Pending"));
        //return ResponseEntity.ok(conferenceService.getConferenceByStatus(status));

    }

    @GetMapping("approvedConference/{status}")
    public ResponseEntity<List<Conference>> getApprovedConference(@PathVariable String status){
        return ResponseEntity.ok(conferenceService.getConferenceByStatus("Approved"));
        //return ResponseEntity.ok(conferenceService.getConferenceByStatus(status));
    }

    @GetMapping("rejectedConference/{status}")
    public ResponseEntity<List<Conference>> getRejectedConference(@PathVariable String status){
        return ResponseEntity.ok(conferenceService.getConferenceByStatus("Rejected"));
        //return ResponseEntity.ok(conferenceService.getConferenceByStatus(status));
    }

    @DeleteMapping("/deleteConference/{id}")
    public ResponseEntity deleteConference(@PathVariable String id) {
        conferenceService.deleteConference(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/approveConference/{id}")
    public ResponseEntity<List<Conference>> approveConference(@RequestBody Conference conference){
        conferenceService.AproveConference(conference);
        return ResponseEntity.ok(conferenceService.getAllConference());

    }

    @GetMapping("/conferencebyid/{id}")
    public Object getConferenceById(@PathVariable String id){
        return ResponseEntity.ok(conferenceService.getConferenceById(id));
    }
}
