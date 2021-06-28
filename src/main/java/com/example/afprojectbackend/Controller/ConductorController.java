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
import java.util.Locale;

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

    @GetMapping("/filter/submission/{status}")
    public ResponseEntity<List<Conductor>> filterConductorBySubmissionStatus(@PathVariable String status) {
        return new ResponseEntity<>(conductorService.ProposalApprovalStatus(status), HttpStatus.OK);
    }

    @GetMapping("/search/conference/{search}")
    public ResponseEntity<List<Conductor>> filterConductorByConferenceId(@PathVariable String search) {
        return new ResponseEntity<>(conductorService.getConductorByConferenceId(search.toUpperCase(Locale.ROOT)), HttpStatus.OK);
    }

    @PutMapping("/update/submission/status")
    public ResponseEntity<?> updateSubmissionStatus(@RequestParam("id") String id, @RequestParam("s_status") String status) {
        conductorService.updateSubmissionStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
