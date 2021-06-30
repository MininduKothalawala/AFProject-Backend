package com.example.afprojectbackend.Service;

import com.example.afprojectbackend.Model.Attendee;
import com.example.afprojectbackend.Model.Conference;
import com.example.afprojectbackend.Repository.AttendeeRepository;
import com.example.afprojectbackend.Repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;
    private final MongoTemplate mongoTemplate;
    private final ConferenceRepository conferenceRepository;

    @Autowired
    public AttendeeService(AttendeeRepository attendeeRepository, MongoTemplate mongoTemplate, ConferenceRepository conferenceRepository) {
        this.attendeeRepository = attendeeRepository;
        this.mongoTemplate = mongoTemplate;
        this.conferenceRepository = conferenceRepository;
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
        return mongoTemplate.find(Query.query(Criteria.where("a_conferenceId").is(cid)), Attendee.class);
    }

    //filter by payment status
    public List<Attendee> PayStatusOfAttendee(String status) {
        return mongoTemplate.find(Query.query(Criteria.where("a_payment_status").is(status)), Attendee.class);
    }

    //update payment status
    public void updatePaymentStatus(String id, String payStatus) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        Update update = new Update();
        update.set("r_pay_status", payStatus);

        mongoTemplate.updateFirst(query, update, Attendee.class);
    }

    public HashMap<String,String> getPaymentDetailsAttendee(String phone) {
        Query query = new Query();
        query.addCriteria(Criteria.where("a_mobileNo").is(phone));

        Attendee attendee = mongoTemplate.findOne(query, Attendee.class);
        Conference conference = conferenceRepository.findConferenceById(attendee.getA_conferenceId());

        HashMap<String, String> paymentDetails = new HashMap<>();
        paymentDetails.put("attendeeId", attendee.getA_id());
        paymentDetails.put("attendeeName", attendee.getA_name());
        paymentDetails.put("attendeeEmail", attendee.getA_email());
        paymentDetails.put("conferenceId", conference.getId());
        paymentDetails.put("conferenceName", conference.getConferenceName());
        paymentDetails.put("amount", conference.getPayment());

        return paymentDetails;
    }
}
