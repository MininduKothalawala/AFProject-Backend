package com.example.afprojectbackend.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class TemplateService {

    private final GridFsTemplate gridFsTemplate;

    @Autowired
    public TemplateService(GridFsTemplate gridFsTemplate) {
        this.gridFsTemplate = gridFsTemplate;
    }

    public String addTemplate(String title, String type, String username, MultipartFile file) throws IOException {

        //define metadata for the file
        DBObject metaData = new BasicDBObject();
        metaData.put("addedBy", username);
        metaData.put("templateType", type);

        //store file in DB
        Object fileId = gridFsTemplate.store(file.getInputStream(), title, file.getContentType(), metaData);

        return fileId.toString();

    }
}
