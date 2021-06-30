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
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "https://af-icaf-frontend.azurewebsites.net")
@RequestMapping("/templates")

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

    //retrieve file
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
    public ResponseEntity<?> uploadTemplate(@RequestParam("desc") String desc, @RequestParam("type") String type,@RequestParam("addedBy") String addedBy,
                                            @RequestParam("tempImg") MultipartFile tempImg, @RequestParam("tempFile") MultipartFile tempFile) throws IOException {

        //saving file to GridFS
        List<String> IDList = templateService.addTemplate(type, addedBy, tempImg, tempFile);

        //saving file to Mongo Collection
        Template template = new Template(desc,type, addedBy, IDList.get(0), tempImg.getOriginalFilename(), IDList.get(1), tempFile.getOriginalFilename());

        return new ResponseEntity<>(templateRepository.save(template), HttpStatus.CREATED);
    }

    //update description only
    @PutMapping("/updateDesc")
    public ResponseEntity<?> updateDesc(@RequestParam("id") String id, @RequestParam("desc") String desc, @RequestParam("addedBy") String username) {
        String res = templateService.updateDescription(id, desc, username);
        return ResponseEntity.ok(res);
    }

    //update all
    @PutMapping("/update")
    public ResponseEntity<?> updateTemplate(@RequestParam("id") String id, @RequestParam("desc") String desc,
                                            @RequestParam("type") String type, @RequestParam("addedBy") String addedBy,
                                            @RequestParam("tempImg") MultipartFile tempImg,
                                            @RequestParam("tempFile") MultipartFile tempFile) throws IOException {

        String res = templateService.updateWithFile(id, desc, type, addedBy, tempImg, tempFile);
        return ResponseEntity.ok(res);
    }

    //delete template
    @DeleteMapping("/{id}/{imgId}/{fileId}")
    public ResponseEntity<?> deleteTemplate(@PathVariable String id, @PathVariable String imgId, @PathVariable String fileId) {
        //delete from GridFs
        templateService.deleteTemplate(fileId);
        templateService.deleteTemplate(imgId);

        //delete from Mongo Collection
        templateRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
