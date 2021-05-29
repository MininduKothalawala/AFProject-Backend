package com.example.afprojectbackend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("Template")
public class Template {
    @Id
    private String id;
    private String tempDesc;
    @Field("tempType")
    private String tempType;
    @Field("addedBy")
    private String username;
    @Field("tempFileId")
    private String tempFileId;
    @Field("filename")
    private String filename;

    public Template() {
    }

    public Template(String id, String tempDesc, String tempType, String username, String tempFileId, String filename) {
        this.id = id;
        this.tempDesc = tempDesc;
        this.tempType = tempType;
        this.username = username;
        this.tempFileId = tempFileId;
        this.filename = filename;
    }

    public Template(String tempDesc, String tempType, String username, String tempFileId, String filename) {
        this.tempDesc = tempDesc;
        this.tempType = tempType;
        this.username = username;
        this.tempFileId = tempFileId;
        this.filename = filename;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTempDesc() {
        return tempDesc;
    }

    public void setTempDesc(String tempDesc) {
        this.tempDesc = tempDesc;
    }

    public String getTempType() {
        return tempType;
    }

    public void setTempType(String tempType) {
        this.tempType = tempType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTempFileId() {
        return tempFileId;
    }

    public void setTempFileId(String tempFileId) {
        this.tempFileId = tempFileId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
