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
    public ResponseEntity<?> addResearcher(@RequestParam("name") String name, @RequestParam("mail") String email,
                                           @RequestParam("mobile") String mobile, @RequestParam("file") MultipartFile file) throws IOException {
        researcherService.addReasearcher(name, email, mobile, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Researcher>> getAllResearchers(){
        return ResponseEntity.ok(researcherService.getAllReasearchers());
    }

    @DeleteMapping("/deleteresearcher/{id}")
    public void deleteResearcher(@PathVariable String id){
        researcherService.deleteResearcher(id);
    }

    @GetMapping("/getresearcher/{id}")
    public Object getResearcherById(@PathVariable String id){
        return researcherService.getResearcherById(id);
    }
}
