package com.example.afprojectbackend.Service;

import com.example.afprojectbackend.Model.Template;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@Service
public class TemplateService {

    private final GridFsTemplate gridFsTemplate;

    private final GridFsOperations operations;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TemplateService(GridFsTemplate gridFsTemplate, GridFsOperations operations, MongoTemplate mongoTemplate) {
        this.gridFsTemplate = gridFsTemplate;
        this.operations = operations;
        this.mongoTemplate = mongoTemplate;
    }

    //add template
    public String addTemplate(String type, String username, MultipartFile file) throws IOException {

        //define metadata for the file
        DBObject metaData = new BasicDBObject();
        metaData.put("addedBy", username);
        metaData.put("templateType", type);

        //store file in DB
        Object fileId = gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType(), metaData);

        return fileId.toString();
    }


    //download template
    public byte[] downloadTemplate(String id) throws IOException {

        //find file from DB
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));

        //setting data to byte array
        byte[] file = new byte[0];

        if (gridFSFile != null) {
            file = IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream());
        }

        return file;
    }

    //sending filename and content type through hashmap
    public HashMap<String, String> getDetailsOfFile(String id) {
        //find file from DB
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));

        HashMap<String, String> template = new HashMap<>();

        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            template.put("contentType", gridFSFile.getMetadata().get("_contentType").toString());
            template.put("filename", gridFSFile.getFilename());
        }

        return template;
    }

    //update description only
    public String updateDescription(String id, String desc, String username) {
        //find query
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id)).fields().include("addedBy");

        //get the added by username
        Template user = mongoTemplate.findOne(query, Template.class);

        //set update field
        Update update = new Update();
        update.set("tempDesc", desc);

        //if content edited by other user, update username
        if (user !=null &&  !user.getUsername().equals(username)) {
            update.set("addedBy", username + "(edited)");
        }

        mongoTemplate.updateFirst(query, update, Template.class);

        return id;
    }

    //update all
    public String updateWithFile(String id, String desc, String type, String username, MultipartFile file) throws IOException {
        //find and delete existing file
        String fileId = deleteTemplate(id);
        String newID = null;
        String newUser = null;


        if (fileId != null) {
            //if file is deleted then insert new file
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(id));

            //get the added by username
            Template user = mongoTemplate.findOne(query, Template.class);

            //if content edited by other user, update username
            if (user != null &&  !user.getUsername().equals(username)) {
                newUser = user.getUsername() + "(edited)";
            } else {
                newUser = username;
            }

            newID = addTemplate(type, newUser, file);

            if (newID != null) {
                //find query and update db

                Update update = new Update();
                update.set("tempDesc", desc);
                update.set("tempType", type);
                update.set("addedBy", newUser);
                update.set("tempFileId", newID);
                update.set("filename", file.getOriginalFilename());

                mongoTemplate.updateFirst(query, update, Template.class);
            }

        }

        return id;
    }

    //delete template
    public String deleteTemplate(String id) {

        //delete File from DB
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(id)));
        return id;
    }


}
