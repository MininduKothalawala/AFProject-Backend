package com.example.afprojectbackend.Controller;

import com.example.afprojectbackend.Model.Template;
import com.example.afprojectbackend.Repository.TemplateRepository;
import com.example.afprojectbackend.Service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

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

    //retrieve all types of templates
    @GetMapping("/all")
    public ResponseEntity<?> getAllTemplates() {
        return new ResponseEntity<>(templateRepository.findAll(), HttpStatus.OK);
    }

    //retrieve a template
    @GetMapping("/{id}")
    public ResponseEntity<?> getTemplate(@PathVariable String id) {
        Optional<Template> template = templateRepository.findById(id);
        return template.map(res -> ResponseEntity.ok().body(res)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //filter by template type
    @GetMapping("/findByType/{type}")
    public ResponseEntity<?> filterTemplateByType(@PathVariable String type) {
        return new ResponseEntity<>(templateRepository.findByTempType(type), HttpStatus.OK);
    }

    //search by added user
    @GetMapping("/findByUser/{user}")
    public ResponseEntity<?> filterTemplateByUser(@PathVariable String user) {
        return new ResponseEntity<>(templateRepository.findByUsernameContains(user), HttpStatus.OK);
    }

    //retrieve template file
    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadTemplate(@PathVariable String id) throws IOException {
        byte[] template = templateService.downloadTemplate(id);

        //get filename and content type
        HashMap<String, String> templateFile = templateService.getDetailsOfFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(templateFile.get("contentType")))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + templateFile.get("filename") + "\"")
                .body(new ByteArrayResource(template));
    }


    //save template
    @PostMapping("/upload")
    public ResponseEntity<?> uploadTemplate(@RequestParam("title") String title, @RequestParam("desc") String desc,
                                            @RequestParam("type") String type, @RequestParam("username") String username,
                                            @RequestParam("file") MultipartFile file) throws IOException {

        //saving file to GridFS
        String id = templateService.addTemplate(type, username, file);

        //saving file to Mongo Collection
        Template template = new Template(title,desc,type,username,id, file.getOriginalFilename());

        return new ResponseEntity<>(templateRepository.save(template), HttpStatus.CREATED);
    }

    //delete template
    @DeleteMapping("/{id}/{fileId}")
    public ResponseEntity<?> deleteTemplate(@PathVariable String id, @PathVariable String fileId) {
        //delete from GridFs
        String res = templateService.deleteTemplate(fileId);

        //delete from Mongo Collection
        templateRepository.deleteById(id);
        return ResponseEntity.ok("File ID: " + res);
    }

}
