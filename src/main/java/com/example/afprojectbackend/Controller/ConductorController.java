package com.example.afprojectbackend.Controller;

import com.example.afprojectbackend.Model.Conductor;
import com.example.afprojectbackend.Service.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/conductor")
public class ConductorController {

    private final ConductorService conductorService;

    @Autowired
    public ConductorController(ConductorService conductorService) {
        this.conductorService = conductorService;
    }

    @GetMapping
    public ResponseEntity<List<Conductor>> getAllCondunctors(){
        return ResponseEntity.ok(conductorService.getAllConductors());
    }


    @PostMapping("/addconductor")
    public ResponseEntity<?> addConductor(@RequestParam("name") String name, @RequestParam("mail") String email, @RequestParam("mobile") String mobile,
                                          @RequestParam("c_id") String conferenceId, @RequestParam("file") MultipartFile file) throws IOException {
        conductorService.addConductor(name, email, mobile, conferenceId, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getconductor/{id}")
    public Object getConductorById(@PathVariable String id){
        return conductorService.getConductorById(id);
    }

    @GetMapping("/filter/c_approval/{status}")
    public ResponseEntity<List<Conductor>> filterConductorBySubmissionStatus(@PathVariable String status) {
        return new ResponseEntity<>(conductorService.ProposalApprovalStatus(status), HttpStatus.OK);
    }

    @GetMapping("/filter/c_conference/{id}")
    public ResponseEntity<List<Conductor>> filterConductorByConferenceId(@PathVariable String id) {
        return new ResponseEntity<>(conductorService.getConductorByConferenceId(id), HttpStatus.OK);
    }

    @PutMapping("/update/conductor/approval")
    public ResponseEntity<?> updateSubmissionStatus(@RequestParam("id") String id, @RequestParam("s_status") String status) {
        conductorService.updateSubmissionStatus(id, status);
        return new ResponseEntity<>(conductorService.getConductorById(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteconductor/{id}")
    public void deleteConductor(@PathVariable String id){
        conductorService.deleteConductor(id);
    }
}
