package com.example.afprojectbackend.Service;

import com.example.afprojectbackend.Model.Conference;
import com.example.afprojectbackend.Model.Researcher;
import com.example.afprojectbackend.Repository.ConferenceRepository;
import com.example.afprojectbackend.Repository.ResearcherRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public class ResearcherService {

    private final ResearcherRepository researcherRepository;

    private final MongoTemplate mongoTemplate;

    private final GridFsTemplate gridFsTemplate;

    private final ConferenceRepository conferenceRepository;


    @Autowired
    public ResearcherService(ResearcherRepository researcherRepository, MongoTemplate mongoTemplate,
                             GridFsTemplate gridFsTemplate, ConferenceRepository conferenceRepository) {
        this.researcherRepository = researcherRepository;
        this.mongoTemplate = mongoTemplate;
        this.gridFsTemplate = gridFsTemplate;
        this.conferenceRepository = conferenceRepository;
    }

    //insert
    public void addReasearcher(String name, String email, String mobile, String conferenceId,
                               MultipartFile file) throws IOException {

        //define metadata for the file
        DBObject metaData = new BasicDBObject();
        metaData.put("type", "Research Papers");
        metaData.put("conferenceID", conferenceId);

        //store file in GridFS
        Object fileId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), metaData);

        if (fileId != null) {
            Researcher researcher = new Researcher();
            researcher.setR_name(name);
            researcher.setR_email(email);
            researcher.setR_mobileNo(mobile);
            researcher.setR_filename(file.getOriginalFilename());
            researcher.setR_fileId(fileId.toString());
            researcher.setR_conferenceId(conferenceId);
            researcher.setR_pay_status("Pending");                  //as the initial status
            researcher.setR_submission_status("Pending");           //as the initial status

            //store other data in the collection
            researcherRepository.insert(researcher);
        }

    }

    //get researcher list
    public List<Researcher> getAllReasearchers() {
        return researcherRepository.findAll();
    }

    //get researcher by ID
    public Object getResearcherById(String id) {
        return researcherRepository.findById(id);
    }

    //filter by payment status
    public List<Researcher> PayStatusOfResearcher(String status) {
        return mongoTemplate.find(Query.query(Criteria.where("r_pay_status").is(status)), Researcher.class);
    }

    //filter by paper approval status
    public List<Researcher> PaperSubmissionStatus(String status) {
        return mongoTemplate.find(Query.query(Criteria.where("r_submission_status").is(status)), Researcher.class);
    }

    //filter by conference Id
    public List<Researcher> getResearcherByConferenceId(String cid) {
        return mongoTemplate.find(Query.query(Criteria.where("r_conferenceId").is(cid)), Researcher.class);
    }

    //update approval
    public void updateSubmissionStatus(String id, String status) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        Update update = new Update();
        update.set("r_submission_status", status);

        mongoTemplate.updateFirst(query, update, Researcher.class);
    }

    //update payment status
    public void updatePaymentStatus(String id, String payStatus) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        Update update = new Update();
        update.set("r_pay_status", payStatus);

        mongoTemplate.updateFirst(query, update, Researcher.class);
    }

    public HashMap<String,String> getPaymentDetailsResearcher(String ResearcherId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(ResearcherId));

        Researcher researcher = mongoTemplate.findOne(query, Researcher.class);
        Conference conference = conferenceRepository.findConferenceById(researcher.getR_conferenceId());

        HashMap<String, String> paymentDetails = new HashMap<>();
        paymentDetails.put("researcherId", researcher.getR_id());
        paymentDetails.put("researcherName", researcher.getR_name());
        paymentDetails.put("researcherEmail", researcher.getR_email());
        paymentDetails.put("conferenceId", conference.getId());
        paymentDetails.put("conferenceName", conference.getConferenceName());
        paymentDetails.put("amount", conference.getPayment());

        return paymentDetails;
    }
}
