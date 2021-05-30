package com.example.afprojectbackend.Service;

import com.example.afprojectbackend.Model.Researcher;
import com.example.afprojectbackend.Repository.ResearcherRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ResearcherService {

    private final ResearcherRepository researcherRepository;

    private final MongoTemplate mongoTemplate;

    private final GridFsTemplate gridFsTemplate;

    private final GridFsOperations operations;


    @Autowired
    public ResearcherService(ResearcherRepository researcherRepository, MongoTemplate mongoTemplate,
                             GridFsTemplate gridFsTemplate, GridFsOperations operations) {
        this.researcherRepository = researcherRepository;
        this.mongoTemplate = mongoTemplate;
        this.gridFsTemplate = gridFsTemplate;
        this.operations = operations;
    }

//    public void addReasearcher(Researcher researcher){
//        researcherRepository.insert(researcher);
//    }

    public void addReasearcher(String name, String email, String mobile,
                               MultipartFile file) throws IOException {

        //define metadata for the file
        DBObject metaData = new BasicDBObject();
        metaData.put("type", "Research Papers");

        //store file in DB
        Object fileId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), metaData);

        if (fileId != null) {
            Researcher researcher = new Researcher();
            researcher.setR_name(name);
            researcher.setR_email(email);
            researcher.setR_mobileNo(mobile);
            researcher.setR_filename(file.getOriginalFilename());
            researcher.setR_fileId(fileId.toString());

            researcherRepository.insert(researcher);
        }


    }

    public List<Researcher> getAllReasearchers() {
        return researcherRepository.findAll();
    }

    public void deleteResearcher(String id) {
        researcherRepository.deleteById(id);
    }

    public Object getResearcherById(String id) {
        return researcherRepository.findById(id);
    }
}
