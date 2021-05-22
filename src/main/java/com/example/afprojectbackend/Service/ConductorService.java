package com.example.afprojectbackend.Service;

import com.example.afprojectbackend.Model.AdminUser;
import com.example.afprojectbackend.Model.Conductor;
import com.example.afprojectbackend.Repository.ConductorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConductorService {

    private final ConductorRepository conductorRepository;

    public ConductorService(ConductorRepository conductorRepository) {
        this.conductorRepository = conductorRepository;
    }

    public void addConductor(Conductor conductor){
        conductorRepository.insert(conductor);
    }

    public List<Conductor> getAllConductors(){
        return conductorRepository.findAll();
    }

    public void deleteConductor(String id){
        conductorRepository.deleteById(id);
    }
}
