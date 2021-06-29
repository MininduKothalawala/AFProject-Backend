package com.example.afprojectbackend;

import com.example.afprojectbackend.Controller.ConductorController;
import com.example.afprojectbackend.Model.Conductor;
import com.example.afprojectbackend.Service.ConductorService;
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

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ConductorTestClass {

    @MockBean
    ConductorService conductorService;

    @Autowired
    ConductorController conductorController;

    @Test
    public void testCreate() throws Exception {

        MockMultipartFile file = new MockMultipartFile("file","hello.txt", MediaType.TEXT_PLAIN_VALUE,"Hello,Wolrd!".getBytes(StandardCharsets.UTF_8));

        conductorService.addConductor("Kalpana","kalpana@gmail.com","0770817059","C0233",file);
        Assertions.assertTrue(true,"Added");

    }

    @Test
    public void testGetAll(){

        List<Conductor> list = new ArrayList<Conductor>();

        Conductor conductor1 = new Conductor("C001","Kalpana", "kalpana@gmail.com","0770817059","helloworld.txt","123","C0233","Pending");
        Conductor conductor2 = new Conductor("C040","Gaweshith", "kalpana@gmail.com","0770817059","helloworld.pdf","1467","C0233","Payed");

        list.add(conductor1);
        list.add(conductor2);

        Mockito.when(conductorService.getAllConductors()).thenReturn(list);

    }

    @Test
    public void testGetConductorByID(){

        List<Conductor> list = new ArrayList<Conductor>();
        Conductor conductor1 = new Conductor("C001","Kalpana", "kalpana@gmail.com","0770817059","helloworld.txt","123","C0233","Pending");
        Conductor conductor2 = new Conductor("C040","Gaweshith", "kalpana@gmail.com","0770817059","helloworld.pdf","1467","C0233","Payed");

        list.add(conductor1);
        list.add(conductor2);

        Mockito.when(conductorService.getConductorById("C040")).thenReturn(list);

    }

    @Test
    public void testGetConductorByConferenceID(){

        List<Conductor> list = new ArrayList<Conductor>();
        Conductor conductor1 = new Conductor("C001","Kalpana", "kalpana@gmail.com","0770817059","helloworld.txt","123","C0233","Pending");
        Conductor conductor2 = new Conductor("C040","Gaweshith", "kalpana@gmail.com","0770817059","helloworld.pdf","1467","C0983","Payed");

        list.add(conductor1);
        list.add(conductor2);

        Mockito.when(conductorService.getConductorByConferenceId("C0233")).thenReturn(list);
    }

    @Test
    public void testGetApprovedConductors(){

        List<Conductor> list = new ArrayList<Conductor>();
        Conductor conductor1 = new Conductor("C001","Kalpana", "kalpana@gmail.com","0770817059","helloworld.txt","123","C0233","Approved");
        Conductor conductor2 = new Conductor("C040","Gaweshith", "kalpana@gmail.com","0770817059","helloworld.pdf","1467","C0983","Rejected");

        list.add(conductor1);
        list.add(conductor2);

        Mockito.when(conductorService.ProposalApprovalStatus("Approved")).thenReturn(list);
    }

    @Test
    public void testUpdateSubmissionStatus(){

        Conductor conductor = new Conductor("C001","Kalpana", "kalpana@gmail.com","0770817059","helloworld.txt","123","C0233","Pending");

        conductorService.updateSubmissionStatus(conductor.getC_id(), "Approved");
        Assertions.assertTrue(true,"Updated Successfully");

    }

}
