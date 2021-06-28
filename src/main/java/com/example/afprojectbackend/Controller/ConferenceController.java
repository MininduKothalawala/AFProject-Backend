package com.example.afprojectbackend.Controller;

import com.example.afprojectbackend.Model.Conference;
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
    public ConferenceController(ConferenceService conferenceService) {
        this.conferenceService = conferenceService;
    }

    @PostMapping("/addConference")
    public ResponseEntity<?> addConference(@RequestBody Conference conference) {

        conferenceService.addConference(conference);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/updateConference")
    public ResponseEntity<List<Conference>> updateConference(@RequestBody Conference conference) {

        conferenceService.updateConference(conference);
        return ResponseEntity.ok(conferenceService.getAllConference());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Conference>> getAllConference() {
        return ResponseEntity.ok(conferenceService.getAllConference());
    }

    @GetMapping("pendingConference/{status}")
    public ResponseEntity<List<Conference>> getPendingConference(@PathVariable String status) {
        return ResponseEntity.ok(conferenceService.getConferenceByStatus("Pending"));

    }

    @GetMapping("approvedConference/{status}")
    public ResponseEntity<List<Conference>> getApprovedConference(@PathVariable String status) {
        return ResponseEntity.ok(conferenceService.getConferenceByStatus("Approved"));
    }

    @GetMapping("rejectedConference/{status}")
    public ResponseEntity<List<Conference>> getRejectedConference(@PathVariable String status) {
        return ResponseEntity.ok(conferenceService.getConferenceByStatus("Rejected"));
    }
    @GetMapping("editedConference/{status}")
    public ResponseEntity<List<Conference>> getEditedConference(@PathVariable String status){
        return ResponseEntity.ok(conferenceService.getConferenceByStatus("Updated"));
    }

    @GetMapping("expiredConference/{status}")
    public ResponseEntity<List<Conference>> getExpiredConference(@PathVariable String status){
        return ResponseEntity.ok(conferenceService.getConferenceByStatus("Expired"));
    }

    @GetMapping("canceledConference/{status}")
    public ResponseEntity<List<Conference>> getCanceledConference(@PathVariable String status){
        return ResponseEntity.ok(conferenceService.getConferenceByStatus("Canceled"));

    }

    @DeleteMapping("/deleteConference/{id}")
    public ResponseEntity<?> deleteConference(@PathVariable String id) {
        conferenceService.deleteConference(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/conferencebyid/{id}")
    public Object getConferenceById(@PathVariable String id) {
        return ResponseEntity.ok(conferenceService.getConferenceById(id));
    }

//    @PutMapping("/approveConference/{id}")
//    public ResponseEntity<List<Conference>> approveConference(@RequestBody Conference conference) {
//        conferenceService.ApproveConference(conference);
//        return ResponseEntity.ok(conferenceService.getAllConference());
//    }

    @PutMapping("/updateStatus/{id}/{status}")
    public ResponseEntity<List<Conference>> updateConferenceStatus(@PathVariable String id, @PathVariable String status) {
        conferenceService.changeStatus(id, status);
        return ResponseEntity.ok(conferenceService.getAllConference());
    }
}
