package com.example.afprojectbackend.Controller;

import com.example.afprojectbackend.Model.Attendee;
import com.example.afprojectbackend.Repository.AttendeeRepository;
import com.example.afprojectbackend.Service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/attendee")
public class AttendeeController {

    @Autowired
    private AttendeeRepository attendeeRepository;

    private final AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @PostMapping("/addattendee")
    public ResponseEntity addAttendee(@RequestBody Attendee attendee){
        attendeeService.addAttendee(attendee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Attendee>> getAllAttendees(){
        return ResponseEntity.ok(attendeeService.getAllAttendees());
    }

    @DeleteMapping("/deleteattendee/{id}")
    public void deleteAttendee(@PathVariable String id){
        attendeeService.deleteAttendee(id);
    }

    @GetMapping("getattendee/{id}")
    public Object getAttendeeById(@PathVariable String id){
        return attendeeService.getAttendeeById(id);
    }
}
