package com.example.afprojectbackend;

import com.example.afprojectbackend.Controller.AttendeeController;
import com.example.afprojectbackend.Model.Attendee;
import com.example.afprojectbackend.Service.AttendeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AttendeeTestClass {

    @MockBean
    AttendeeService attendeeService;

    @Autowired
    AttendeeController attendeeController;

    @Test
    public void testCreate(){

        Attendee attendee = new Attendee("A123","Kalpana","kalpana@gmail.com","0770817059","C0233","Pending","Null");
        attendeeService.addAttendee(attendee);
        Assertions.assertTrue(true,"Added");

    }

    @Test
    public void testUpdate(){

        Attendee attendee = new Attendee("A123","Kalpana","kalpana@gmail.com","0770817059","C0233","Pending","Null");
        attendeeService.addAttendee(attendee);
        attendeeService.updatePaymentStatus("A123","Payed");
        Assertions.assertTrue(true,"Payment Status Updated");

    }

    @Test
    public void testGetAll(){


        List<Attendee> list = new ArrayList<Attendee>();

        Attendee attendee1 = new Attendee("A123","Kalpana","kalpana@gmail.com","0770817059","C0233","Pending","Null");
        Attendee attendee2 = new Attendee("A345","Kalpana","kalpana@gmail.com","0770817059","C0233","Pending","Null");

        list.add(attendee1);
        list.add(attendee2);

        Mockito.when(attendeeService.getAllAttendees()).thenReturn(list);

    }


    @Test
    public void testGetAttendeeByID(){

        List<Attendee> list = new ArrayList<Attendee>();
        Attendee attendee1 = new Attendee("A123","Kalpana","kalpana@gmail.com","0770817059","C0233","Pending","Null");
        Attendee attendee2 = new Attendee("A345","Gaweshith","gaweshith@gmail.com","0710823459","C0243","Payed","2021-06-18");
        list.add(attendee1);
        list.add(attendee2);

        Mockito.when(attendeeService.getAttendeeById("A123")).thenReturn(list);
    }

    @Test
    public void testGetAttendeeByConferenceID(){

        List<Attendee> list = new ArrayList<Attendee>();

        Attendee attendee1 = new Attendee("A123","Kalpana","kalpana@gmail.com","0770817059","C0233","Pending","Null");
        Attendee attendee2 = new Attendee("A345","Gaweshith","gaweshith@gmail.com","0772817499","C0783","Pending","Null");

        list.add(attendee1);
        list.add(attendee2);

        Mockito.when(attendeeService.getAttendeeByConferenceId("C023")).thenReturn(list);
    }

    @Test
    public void testGetAttendeeByPayStatus(){

        List<Attendee> list = new ArrayList<Attendee>();

        Attendee attendee1 = new Attendee("A123","Kalpana","kalpana@gmail.com","0770817059","C0233","Pending","Null");
        Attendee attendee2 = new Attendee("A345","Gaweshith","gaweshith@gmail.com","0772817499","C0783","Pending","Null");

        list.add(attendee1);
        list.add(attendee2);

        Mockito.when(attendeeService.PayStatusOfAttendee("Payed")).thenReturn(list);
    }
}
