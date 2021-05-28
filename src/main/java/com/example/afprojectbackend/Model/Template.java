package com.example.afprojectbackend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.InputStream;

@Document("Template")
public class Template {
    @Id
    private String id;
    @Transient
    private InputStream inputStream;
    @Transient
    private String contentType;
    @Field("tempTitle")
    private String tempTitle;
    @Field("tempDesc")
    private String tempDesc;
    @Field("tempType")
    private String tempType;
    @Field("addedBy")
    private String username;
    @Field("tempFileId")
    private String tempFileId;

    public Template() {
    }

    public Template(String id, InputStream inputStream, String contentType, String tempTitle,
                    String tempDesc, String tempType, String username, String tempFileId) {
        this.id = id;
        this.inputStream = inputStream;
        this.contentType = contentType;
        this.tempTitle = tempTitle;
        this.tempDesc = tempDesc;
        this.tempType = tempType;
        this.username = username;
        this.tempFileId = tempFileId;
    }

    public Template(String tempTitle, String tempDesc, String tempType, String username, String tempFileId) {
        this.tempTitle = tempTitle;
        this.tempDesc = tempDesc;
        this.tempType = tempType;
        this.username = username;
        this.tempFileId = tempFileId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getTempTitle() {
        return tempTitle;
    }

    public void setTempTitle(String tempTitle) {
        this.tempTitle = tempTitle;
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
}
