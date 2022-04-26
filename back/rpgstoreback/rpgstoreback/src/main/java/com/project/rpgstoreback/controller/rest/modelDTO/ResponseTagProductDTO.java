package com.project.rpgstoreback.controller.rest.modelDTO;

import java.util.List;

public class ResponseTagProductDTO {

    private Long id;
    private String title;
    private String description;
    private String creator;
    private List<String> pictures;

    public ResponseTagProductDTO() {
    }

    public ResponseTagProductDTO(Long id, String title, String description,String creator, List<String> pictures) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.pictures = pictures;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }


}
