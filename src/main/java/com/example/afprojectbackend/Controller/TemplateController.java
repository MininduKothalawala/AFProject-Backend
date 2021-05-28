package com.example.afprojectbackend.Controller;

import com.example.afprojectbackend.Model.Template;
import com.example.afprojectbackend.Repository.TemplateRepository;
import com.example.afprojectbackend.Service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/templates")
@CrossOrigin(origins = "http://localhost:3000")
public class TemplateController {

    private final TemplateRepository templateRepository;

    private final TemplateService templateService;


    @Autowired
    public TemplateController(TemplateRepository templateRepository, TemplateService templateService) {
        this.templateRepository = templateRepository;
        this.templateService = templateService;
    }


    @PostMapping("/upload")
    public ResponseEntity<?> uploadTemplate(@RequestParam("title") String title, @RequestParam("desc") String desc,
                                            @RequestParam("type") String type, @RequestParam("username") String username,
                                            @RequestParam("file") MultipartFile file) throws IOException {

        String id = templateService.addTemplate(title, type, username, file);
        Template template = new Template(title,desc,type,username,id);

        return new ResponseEntity<>(templateRepository.save(template), HttpStatus.CREATED);
    }



}
