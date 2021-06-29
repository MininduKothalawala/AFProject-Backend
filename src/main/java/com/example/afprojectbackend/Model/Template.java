package com.example.afprojectbackend.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Template")
public class Template {
    @Id
    private String id;
    private String tempDesc;
    private String tempType;
    private String addedBy;
    private String imgFileId;
    private String imgFileName;
    private String tempFileId;
    private String tempFileName;

    public Template() {
    }

    public Template(String id, String tempDesc, String tempType, String addedBy, String imgFileId, String imgFileName, String tempFileId, String tempFileName) {
        this.id = id;
        this.tempDesc = tempDesc;
        this.tempType = tempType;
        this.addedBy = addedBy;
        this.imgFileId = imgFileId;
        this.imgFileName = imgFileName;
        this.tempFileId = tempFileId;
        this.tempFileName = tempFileName;
    }

    public Template(String tempDesc, String tempType, String addedBy, String imgFileId, String imgFileName, String tempFileId, String tempFileName) {
        this.tempDesc = tempDesc;
        this.tempType = tempType;
        this.addedBy = addedBy;
        this.imgFileId = imgFileId;
        this.imgFileName = imgFileName;
        this.tempFileId = tempFileId;
        this.tempFileName = tempFileName;
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

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getImgFileId() {
        return imgFileId;
    }

    public void setImgFileId(String imgFileId) {
        this.imgFileId = imgFileId;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public String getTempFileId() {
        return tempFileId;
    }

    public void setTempFileId(String tempFileId) {
        this.tempFileId = tempFileId;
    }

    public String getTempFileName() {
        return tempFileName;
    }

    public void setTempFileName(String tempFileName) {
        this.tempFileName = tempFileName;
    }

}
