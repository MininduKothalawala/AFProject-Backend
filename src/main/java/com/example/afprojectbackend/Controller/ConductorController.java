package com.example.afprojectbackend.Controller;

import com.example.afprojectbackend.Model.Conductor;
import com.example.afprojectbackend.Repository.ConductorRepository;
import com.example.afprojectbackend.Service.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/conductor")
public class ConductorController {

    @Autowired
    private ConductorRepository conductorRepository;

    private final ConductorService conductorService;

    public ConductorController(ConductorService conductorService) {
        this.conductorService = conductorService;
    }

    @GetMapping
    public ResponseEntity<List<Conductor>> getAllCondunctors(){
        return ResponseEntity.ok(conductorService.getAllConductors());
    }

    @PostMapping("/addconductor")
    public ResponseEntity addConductor(@RequestBody Conductor conductor){
        conductorService.addConductor(conductor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/deleteconductor/{id}")
    public void deleteConductor(@PathVariable String id){
        conductorService.deleteConductor(id);
    }
}
