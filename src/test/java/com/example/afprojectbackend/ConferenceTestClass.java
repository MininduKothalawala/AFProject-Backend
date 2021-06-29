package com.example.afprojectbackend;

import com.example.afprojectbackend.Controller.ConferenceController;
import com.example.afprojectbackend.Model.Conference;
import com.example.afprojectbackend.Service.ConferenceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ConferenceTestClass {

    @MockBean
    ConferenceService conferenceService;

    @Autowired
    ConferenceController conferenceController;

    @Test
    public void testCreate() throws Exception{

        Conference conference = new Conference("C0233", "IT","Test", "2020-10-09","2020-10-10","SLIIT","Pending","1000");
        conferenceService.addConference(conference);
        Assertions.assertTrue(true,"Added");

    }

    @Test
    public void testUpdate(){

        Conference conference = new Conference("C0233", "Software","Test", "2020-10-09","2020-10-10","SLIIT","Pending","1000");
        conferenceService.updateConference(conference);
        Assertions.assertTrue(true,"Added");


    }

    @Test
    public void testGetAll(){

        Conference conference = new Conference("C0233", "IT","Test", "2020-10-09","2020-10-10","SLIIT","Pending","1000");

        List<Conference> conferenceList = Arrays.asList(conference);
        Mockito.when(conferenceService.getAllConference()).thenReturn(conferenceList);
    }

    @Test
    public void testDelete(){

        Conference conference = new Conference("C0233", "IT","Test", "2020-10-09","2020-10-10","SLIIT","Pending","1000");
        conferenceService.deleteConference(conference.getId());
        Assertions.assertTrue(true,"Deleted");

    }

    @Test
    public void testGetConferenceByID(){

        List<Conference> list = new ArrayList<Conference>();

        Conference conference1 = new Conference("C0233", "IT","Test", "2020-10-09","2020-10-10","SLIIT","Pending","1000");
        Conference conference2 = new Conference("C0283", "IT","Test", "2020-10-09","2020-10-10","SLIIT","Approved","1000");

        list.add(conference1);
        list.add(conference2);

        conferenceService.getConferenceById("C0233");
        Assertions.assertTrue(true,"ConferenceByID");
    }

    @Test
    public void testGetConferenceByStatus(){

        List<Conference> list = new ArrayList<Conference>();

        Conference conference1 = new Conference("C0233", "IT","Test", "2020-10-09","2020-10-10","SLIIT","Pending","1000");
        Conference conference2 = new Conference("C0283", "IT","Test", "2020-10-09","2020-10-10","SLIIT","Approved","1000");
        Conference conference3 = new Conference("C0293", "IT","Test", "2020-10-09","2020-10-10","SLIIT","Pending","1000");

        list.add(conference1);
        list.add(conference2);
        list.add(conference3);

        Mockito.when(conferenceService.getConferenceByStatus("Pending")).thenReturn(list);
    }
}
