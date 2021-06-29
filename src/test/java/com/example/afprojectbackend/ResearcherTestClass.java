package com.example.afprojectbackend;

import com.example.afprojectbackend.Controller.ResearcherController;
import com.example.afprojectbackend.Model.Researcher;
import com.example.afprojectbackend.Service.ResearcherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ResearcherTestClass {

    @MockBean
    ResearcherService researcherService;

    @Autowired
    ResearcherController researcherController;

    @Test
    public void testCreate() throws IOException {

        MockMultipartFile file = new MockMultipartFile("file","hello.txt", MediaType.TEXT_PLAIN_VALUE,"Hello,Wolrd!".getBytes(StandardCharsets.UTF_8));

        researcherService.addReasearcher("Kalpana","kalpana@gmail.com","0770817059","C0233",file);
        Assertions.assertTrue(true,"Added");
    }

    @Test
    public void testGetAll(){

        List<Researcher> list = new ArrayList<Researcher>();

        Researcher researcher1 = new Researcher("R001", "Kalpana", "kalpana@gmail.com","0770817059","helloworld.odf","1467","C023","Pending","Pending","Null");
        Researcher researcher2 = new Researcher("C040","Gaweshith", "kalpana@gmail.com","0770817059","helloworld.pdf","1467","C0233","Approved","Payed","2021-06-13");

        list.add(researcher1);
        list.add(researcher2);

        Mockito.when(researcherService.getAllReasearchers()).thenReturn(list);
    }

    @Test
    public void testGetResearcherByID(){

        List<Researcher> list = new ArrayList<Researcher>();

        Researcher researcher1 = new Researcher("R001", "Kalpana", "kalpana@gmail.com","0770817059","helloworld.odf","1467","C023","Pending","Pending","Null");
        Researcher researcher2 = new Researcher("R040","Gaweshith", "kalpana@gmail.com","0770817059","helloworld.pdf","1467","C0233","Approved","Payed","2021-06-20");

        list.add(researcher1);
        list.add(researcher2);

        Mockito.when(researcherService.getResearcherById("R040")).thenReturn(list);

    }

    @Test
    public void testGetResearcherByPayStatus(){

        List<Researcher> list = new ArrayList<Researcher>();

        Researcher researcher1 = new Researcher("R001", "Kalpana", "kalpana@gmail.com","0770817059","helloworld.odf","1467","C023","Pending","Pending","Null");
        Researcher researcher2 = new Researcher("R040","Gaweshith", "kalpana@gmail.com","0770817059","helloworld.pdf","1467","C0233","Approved","Payed","2021-06-16");

        list.add(researcher1);
        list.add(researcher2);

        Mockito.when(researcherService.PayStatusOfResearcher("Payed")).thenReturn(list);

    }

    @Test
    public void testGetResearcherPaperApproval(){

        List<Researcher> list = new ArrayList<Researcher>();

        Researcher researcher1 = new Researcher("R001", "Kalpana", "kalpana@gmail.com","0770817059","helloworld.odf","1467","C023","Pending","Pending","Null");
        Researcher researcher2 = new Researcher("R040","Gaweshith", "kalpana@gmail.com","0770817059","helloworld.pdf","1467","C0233","Approved","Payed","2021-06-18");

        list.add(researcher1);
        list.add(researcher2);

        Mockito.when(researcherService.PaperSubmissionStatus("Approved")).thenReturn(list);

    }

    @Test
    public void testGetResearcherByConferenceID(){

        List<Researcher> list = new ArrayList<Researcher>();

        Researcher researcher1 = new Researcher("R001", "Kalpana", "kalpana@gmail.com","0770817059","helloworld.odf","1467","C0245","Pending","Pending","Null");
        Researcher researcher2 = new Researcher("R040","Gaweshith", "kalpana@gmail.com","0770817059","helloworld.pdf","1467","C0233","Approved","Payed","2021-06-19");

        list.add(researcher1);
        list.add(researcher2);

        Mockito.when(researcherService.getResearcherByConferenceId("C0233")).thenReturn(list);

    }

    @Test
    public void testUpdateSubmissionStatus(){

        Researcher researcher = new Researcher("R001","Kalpana", "kalpana@gmail.com","0770817059","helloworld.txt","123","C0233","Pending","Payed","2021-06-07");

        researcherService.updateSubmissionStatus(researcher.getR_id(), "Approved");
        Assertions.assertTrue(true,"Submission Status Updated Successfully");

    }

    @Test
    public void testUpdatePaymentStatus(){

        Researcher researcher = new Researcher("R001","Kalpana", "kalpana@gmail.com","0770817059","helloworld.txt","123","C0233","Pending","Pending","Null");

        researcherService.updatePaymentStatus(researcher.getR_id(), "Payed");
        Assertions.assertTrue(true,"Payment Status Updated Successfully");

    }



}
