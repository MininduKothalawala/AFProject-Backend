package com.example.afprojectbackend.Controller;

import com.example.afprojectbackend.Model.Researcher;
import com.example.afprojectbackend.Repository.ResearcherRepository;
import com.example.afprojectbackend.Service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/researcher")
public class ResearcherController {

    @Autowired
    private ResearcherRepository researcherRepository;

    private final ResearcherService researcherService;

    public ResearcherController(ResearcherService researcherService) {
        this.researcherService = researcherService;
    }

    @PostMapping("/addresearcher")
    public ResponseEntity addResearcher(@RequestBody Researcher researcher){
        researcherService.addReasearcher(researcher);
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
}
