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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public List<String> addTemplate(String type, String addedBy, MultipartFile tempImg, MultipartFile tempFile) throws IOException {

        //store preview image to DB
        DBObject imgMetaData = new BasicDBObject();
        imgMetaData.put("addedBy", addedBy);
        Object imgFileId = gridFsTemplate.store(tempImg.getInputStream(), tempImg.getOriginalFilename(), tempImg.getContentType(), imgMetaData);


        //define metadata for the file
        DBObject tempMetaData = new BasicDBObject();
        tempMetaData.put("addedBy", addedBy);
        tempMetaData.put("templateType", type);
        tempMetaData.put("imgFileID", imgFileId.toString());
        tempMetaData.put("imgFileName", tempImg.getOriginalFilename());

        //store file in DB
        Object tempFileId = gridFsTemplate.store(tempFile.getInputStream(), tempFile.getOriginalFilename(), tempFile.getContentType(), tempMetaData);

        List<String> templateIDList = new ArrayList<>();
        templateIDList.add(0, imgFileId.toString());
        templateIDList.add(1, tempFileId.toString());

        return templateIDList;
    }

    //download template file
    public byte[] downloadTemplate(String tempFileID) throws IOException {

        //find file from DB
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(tempFileID)));

        //setting data to byte array
        byte[] file = new byte[0];

        if (gridFSFile != null) {
            file = IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream());
        }

        return file;
    }

    //sending filename and content type through hashmap <- part of DOWNLOAD process
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

        /*
         * get the added by username,
         * to check which user is editing
         */
        Template user = mongoTemplate.findOne(query, Template.class);

        //set update field
        Update update = new Update();
        update.set("tempDesc", desc);

        //if content edited by other user, update username
        if (user !=null &&  !user.getAddedBy().equals(username)) {
            update.set("addedBy", username + "(edited)");
        }

        mongoTemplate.updateFirst(query, update, Template.class);

        return id;
    }

    //update all
//    public String updateWithFile(String id, String desc, String type, String username, MultipartFile file) throws IOException {
//        //find and delete existing file
//        String fileId = deleteTemplate(id);
//        String newID = null;
//        String newUser = null;
//
//
//        if (fileId != null) {
//            //if file is deleted then insert new file
//            Query query = new Query();
//            query.addCriteria(Criteria.where("id").is(id));
//
//            //get the added by username
//            Template user = mongoTemplate.findOne(query, Template.class);
//
//            //if content edited by other user, update username
//            if (user != null &&  !user.getAddedBy().equals(username)) {
//                newUser = user.getAddedBy() + "(edited)";
//            } else {
//                newUser = username;
//            }
//
//            newID = addTemplate(type, newUser, file);
//
//            if (newID != null) {
//                //find query and update db
//
//                Update update = new Update();
//                update.set("tempDesc", desc);
//                update.set("tempType", type);
//                update.set("addedBy", newUser);
//                update.set("tempFileId", newID);
//                update.set("filename", file.getOriginalFilename());
//
//                mongoTemplate.updateFirst(query, update, Template.class);
//            }
//
//        }
//
//        return id;
//    }

    //delete template
    public String deleteTemplate(String id) {

        //delete File from DB
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(id)));
        return id;
    }


}
