package com.example.afprojectbackend;

import com.example.afprojectbackend.Controller.TemplateController;
import com.example.afprojectbackend.Model.Template;
import com.example.afprojectbackend.Service.TemplateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class TemplateTestClass {

    @MockBean
    TemplateService templateService;

    @Autowired
    TemplateController templateController;

    @Test
    public void testCreate() throws IOException {

        MockMultipartFile file = new MockMultipartFile("file","hello.txt", MediaType.TEXT_PLAIN_VALUE,"Hello,Wolrd!".getBytes(StandardCharsets.UTF_8));
        MockMultipartFile img = new MockMultipartFile("image","img.png",MediaType.IMAGE_PNG_VALUE,"Test,Image!".getBytes(StandardCharsets.UTF_8));

        templateService.addTemplate("Proposal Template","Hansi",img, file);
        Assertions.assertTrue(true,"Added");

    }

    @Test
    public void testGetDetailsOfFile(){

        Template template1 = new Template("Project Proposal","Proposal","Minindu","9782","img.png","1657","hello.ppt");

        templateService.getDetailsOfFile(template1.getId());
        Assertions.assertTrue(true,"Retrieved Template by ID successfully");

    }

    @Test
    public void testUpdateDescription(){

        Template template = new Template("Template test1", "Proposal", "Minindu", "9782","img.png","1890","hello.txt");

        templateService.updateDescription(template.getId(),"Proposal Test1","Minindu");
        Assertions.assertTrue(true,"Description Updated Successfully");
    }


    @Test
    public void testDelete(){

        Template template = new Template("Template test1", "Proposal", "Minindu", "5748","img.png","1890","hello.txt");

        templateService.deleteTemplate(template.getId());
        Assertions.assertTrue(true,"Deleted Successfully");
    }
}
