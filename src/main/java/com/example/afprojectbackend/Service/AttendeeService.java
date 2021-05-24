package com.example.afprojectbackend.Service;

import com.example.afprojectbackend.Model.Attendee;
import com.example.afprojectbackend.Repository.AttendeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;

    public AttendeeService(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    public void addAttendee(Attendee attendee){
        attendeeRepository.insert(attendee);
    }

    public List<Attendee> getAllAttendees(){
        return attendeeRepository.findAll();
    }

    public void deleteAttendee(String id){
        attendeeRepository.deleteById(id);
    }

    public Object getAttendeeById(String id){
        return attendeeRepository.findById(id);
    }
}
