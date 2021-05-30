package com.example.afprojectbackend.Service;

import com.example.afprojectbackend.Model.Conductor;
import com.example.afprojectbackend.Repository.ConductorRepository;
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
public class ConductorService {

    private final ConductorRepository conductorRepository;

    private final MongoTemplate mongoTemplate;

    private final GridFsTemplate gridFsTemplate;

    private final GridFsOperations operations;


    @Autowired
    public ConductorService(ConductorRepository conductorRepository, MongoTemplate mongoTemplate,
                             GridFsTemplate gridFsTemplate, GridFsOperations operations) {
        this.conductorRepository = conductorRepository;
        this.mongoTemplate = mongoTemplate;
        this.gridFsTemplate = gridFsTemplate;
        this.operations = operations;
    }

    public void addConductor(String name, String email, String mobile,
                               MultipartFile file) throws IOException {

        //define metadata for the file
        DBObject metaData = new BasicDBObject();
        metaData.put("type", "Workshop Proposal");

        //store file in DB
        Object fileId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), metaData);

        if (fileId != null) {
            Conductor conductor = new Conductor();
            conductor.setC_name(name);
            conductor.setC_email(email);
            conductor.setC_mobileNo(mobile);
            conductor.setC_filename(file.getOriginalFilename());
            conductor.setC_fileId(fileId.toString());

            conductorRepository.insert(conductor);
        }

    }

    public List<Conductor> getAllConductors(){
        return conductorRepository.findAll();
    }

    public void deleteConductor(String id){
        conductorRepository.deleteById(id);
    }

    public Object getConductorById(String id){
        return conductorRepository.findById(id);
    }
}
