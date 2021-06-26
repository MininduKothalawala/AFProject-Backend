package com.example.afprojectbackend.Controller;

import com.example.afprojectbackend.Model.Researcher;
import com.example.afprojectbackend.Service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/researcher")
@CrossOrigin(origins = "http://localhost:3000")
public class ResearcherController {

    private final ResearcherService researcherService;

    @Autowired
    public ResearcherController(ResearcherService researcherService) {
        this.researcherService = researcherService;
    }

    @PostMapping("/addresearcher")
    public ResponseEntity<?> addResearcher(@RequestParam("name") String name, @RequestParam("mail") String email, @RequestParam("mobile") String mobile,
                                           @RequestParam("c_id") String conferenceId, @RequestParam("file") MultipartFile file) throws IOException {
        researcherService.addReasearcher(name, email, mobile, conferenceId, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Researcher>> getAllResearchers(){
        return ResponseEntity.ok(researcherService.getAllReasearchers());
    }

    @GetMapping("/getresearcher/{id}")
    public Object getResearcherById(@PathVariable String id){
        return researcherService.getResearcherById(id);
    }

    @GetMapping("/filter/r_pay/{status}")
    public ResponseEntity<List<Researcher>> filterResearcherByPayment(@PathVariable String status) {
        return new ResponseEntity<>(researcherService.PayStatusOfResearcher(status), HttpStatus.OK);
    }

    @GetMapping("/filterr/r_approval/{status}")
    public ResponseEntity<List<Researcher>> filterResearcherBySubmissionStatus(@PathVariable String status) {
        return new ResponseEntity<>(researcherService.PaperApprovalStatus(status), HttpStatus.OK);
    }

    @GetMapping("/filter/r_conference/{id}")
    public ResponseEntity<List<Researcher>> filterResearcherByConferenceId(@PathVariable String id) {
        return new ResponseEntity<>(researcherService.getResearcherByConferenceId(id), HttpStatus.OK);
    }

    @PutMapping("/update/research/payment")
    public ResponseEntity<?> updatePaymentStatus(@RequestParam("id") String id, @RequestParam("p_status") String status) {
        researcherService.updatePaymentStatus(id, status);
        return new ResponseEntity<>(researcherService.getResearcherById(id), HttpStatus.OK);
    }

    @PutMapping("/update/research/approval")
    public ResponseEntity<?> updateSubmissionStatus(@RequestParam("id") String id, @RequestParam("s_status") String status) {
        researcherService.updateSubmissionStatus(id, status);
        return new ResponseEntity<>(researcherService.getResearcherById(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteresearcher/{id}")
    public void deleteResearcher(@PathVariable String id){
        researcherService.deleteResearcher(id);
    }
}
