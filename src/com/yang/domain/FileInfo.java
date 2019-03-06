package com.yang.domain;

import java.util.Date;

public class FileInfo {
    private int id;
    private int uploaderid;
    private String filename;
    private Date date;
    private String description;

    private String uploader;  //上传者昵称


    @Override
    public String toString() {
        return "FileInfo{" +
                "id=" + id +
                ", uploaderid=" + uploaderid +
                ", filename='" + filename + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getUploaderid() {
        return uploaderid;
    }

    public void setUploaderid(int uploaderid) {
        this.uploaderid = uploaderid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
