package com.example.afprojectbackend.Service;

import com.example.afprojectbackend.Model.Attendee;
import com.example.afprojectbackend.Model.Researcher;
import com.example.afprojectbackend.Repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public AttendeeService(AttendeeRepository attendeeRepository, MongoTemplate mongoTemplate) {
        this.attendeeRepository = attendeeRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public void addAttendee(Attendee attendee){
        attendeeRepository.insert(attendee);
    }

    public List<Attendee> getAllAttendees(){
        return attendeeRepository.findAll();
    }

    public Object getAttendeeById(String id){
        return attendeeRepository.findById(id);
    }

    //filter by conference Id
    public List<Attendee> getAttendeeByConferenceId(String cid) {
        return attendeeRepository.findByA_conferenceId(cid);
    }

    //filter by payment status
    public List<Attendee> PayStatusOfAttendee(String status) {
        return attendeeRepository.findByA_payment_status(status);
    }

    //update payment status
    public void updatePaymentStatus(String id, String payStatus) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        Update update = new Update();
        update.set("r_pay_status", payStatus);

        mongoTemplate.updateFirst(query, update, Researcher.class);
    }

    public void deleteAttendee(String id){
        attendeeRepository.deleteById(id);
    }
}
