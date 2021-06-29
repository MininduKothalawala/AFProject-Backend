package com.example.afprojectbackend.Controller;

import com.example.afprojectbackend.Model.Attendee;
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

    private final AttendeeService attendeeService;

    @Autowired
    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @PostMapping("/addattendee")
    public ResponseEntity<?> addAttendee(@RequestBody Attendee attendee){
        attendeeService.addAttendee(attendee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Attendee>> getAllAttendees(){
        return ResponseEntity.ok(attendeeService.getAllAttendees());
    }

    @GetMapping("getattendee/{id}")
    public Object getAttendeeById(@PathVariable String id){
        return attendeeService.getAttendeeById(id);
    }

    @GetMapping("/filter/a_conference/{id}")
    public ResponseEntity<List<Attendee>> filterAttendeeByConferenceId(@PathVariable String id) {
        return new ResponseEntity<>(attendeeService.getAttendeeByConferenceId(id), HttpStatus.OK);
    }

    @GetMapping("/filter/a_pay/{status}")
    public ResponseEntity<List<Attendee>> filterAttendeeByPayment(@PathVariable String status) {
        return new ResponseEntity<>(attendeeService.PayStatusOfAttendee(status), HttpStatus.OK);
    }

    @PutMapping("/update/payment/status")
    public ResponseEntity<?> updatePaymentStatus(@RequestParam("id") String id, @RequestParam("p_status") String status) {
        attendeeService.updatePaymentStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/payment/{AttendeeId}")
    public ResponseEntity<?> getAttendeeDetailsForPayments(@PathVariable String AttendeeId) {
        return new ResponseEntity<>(attendeeService.getPaymentDetailsAttendee(AttendeeId), HttpStatus.OK);
    }
}
