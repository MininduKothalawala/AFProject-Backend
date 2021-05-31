package com.example.afprojectbackend.Service;

import com.example.afprojectbackend.Model.Conductor;
import com.example.afprojectbackend.Model.Researcher;
import com.example.afprojectbackend.Repository.ConductorRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

    //insert
    public void addConductor(String name, String email, String mobile, String conferenceId,
                               MultipartFile file) throws IOException {

        //define metadata for the file
        DBObject metaData = new BasicDBObject();
        metaData.put("type", "Workshop Proposal");
        metaData.put("conferenceID", conferenceId);

        //store file in GridFS
        Object fileId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), metaData);

        if (fileId != null) {
            Conductor conductor = new Conductor();
            conductor.setC_name(name);
            conductor.setC_email(email);
            conductor.setC_mobileNo(mobile);
            conductor.setC_filename(file.getOriginalFilename());
            conductor.setC_fileId(fileId.toString());
            conductor.setC_conferenceId(conferenceId);
            conductor.setC_submission_status("Pending");           //as the initial status

            //store other data in the collection
            conductorRepository.insert(conductor);
        }

    }

    //get conductor list
    public List<Conductor> getAllConductors(){
        return conductorRepository.findAll();
    }

    //get conductor by ID
    public Object getConductorById(String id){
        return conductorRepository.findById(id);
    }

    //filter by paper approval status
    public List<Conductor> ProposalApprovalStatus(String status) {
        return mongoTemplate.find(Query.query(Criteria.where("c_submission_status").is(status)), Conductor.class);
    }

    //filter by conference Id
    public List<Conductor> getConductorByConferenceId(String cid) {
        return mongoTemplate.find(Query.query(Criteria.where("c_conferenceId").is(cid)), Conductor.class);
    }

    //update approval
    public void updateSubmissionStatus(String id, String status) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        Update update = new Update();
        update.set("c_submission_status", status);

        mongoTemplate.updateFirst(query, update, Researcher.class);
    }

    //delete
    public void deleteConductor(String id){
        conductorRepository.deleteById(id);
    }
}
