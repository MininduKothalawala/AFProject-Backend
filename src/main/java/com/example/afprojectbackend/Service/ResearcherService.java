package com.example.afprojectbackend.Service;

import com.example.afprojectbackend.Model.Researcher;
import com.example.afprojectbackend.Repository.ResearcherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResearcherService {

    private final ResearcherRepository researcherRepository;

    public ResearcherService(ResearcherRepository researcherRepository) {
        this.researcherRepository = researcherRepository;
    }

    public void addReasearcher(Researcher researcher){
        researcherRepository.insert(researcher);
    }

    public List<Researcher> getAllReasearchers(){
        return researcherRepository.findAll();
    }

    public void deleteResearcher(String id){
        researcherRepository.deleteById(id);
    }

    public Object getResearcherById(String id){
        return researcherRepository.findById(id);
    }
}
