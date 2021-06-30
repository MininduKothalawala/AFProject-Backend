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
import java.util.Locale;

@RestController
@CrossOrigin(origins = "https://af-icaf-frontend.azurewebsites.net")
@RequestMapping("/api/researcher")
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

    @GetMapping("/filter/payment/{status}")
    public ResponseEntity<List<Researcher>> filterResearcherByPayment(@PathVariable String status) {
        return new ResponseEntity<>(researcherService.PayStatusOfResearcher(status), HttpStatus.OK);
    }

    @GetMapping("/filter/submission/{status}")
    public ResponseEntity<List<Researcher>> filterResearcherBySubmissionStatus(@PathVariable String status) {
        return new ResponseEntity<>(researcherService.PaperSubmissionStatus(status), HttpStatus.OK);
    }

    @GetMapping("/search/conference/{search}")
    public ResponseEntity<List<Researcher>> filterResearcherByConferenceId(@PathVariable String search) {
        return new ResponseEntity<>(researcherService.getResearcherByConferenceId(search.toUpperCase(Locale.ROOT)), HttpStatus.OK);
    }

    @PutMapping("/update/payment/status")
    public ResponseEntity<?> updatePaymentStatus(@RequestParam("id") String id, @RequestParam("p_status") String status) {
        researcherService.updatePaymentStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update/submission/status")
    public ResponseEntity<?> updateSubmissionStatus(@RequestParam("id") String id, @RequestParam("s_status") String status) {
        researcherService.updateSubmissionStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/payment/researcher/{ResearcherId}")
    public ResponseEntity<?> getResearcherDetailsForPayments(@PathVariable String ResearcherId) {
        return new ResponseEntity<>(researcherService.getPaymentDetailsResearcher(ResearcherId), HttpStatus.OK);
    }
}
