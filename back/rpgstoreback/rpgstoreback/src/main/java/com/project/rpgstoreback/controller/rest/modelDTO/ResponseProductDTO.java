package com.project.rpgstoreback.controller.rest.modelDTO;

import com.project.rpgstoreback.models.Account;
import com.project.rpgstoreback.models.Tag;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public abstract class ResponseProductDTO {

    private Long id;
    private String title;
    private String description;
    private int quantity;
    private long price;
    private ResponseAccountProductDto creator;
    private List<String> pictures;
    private List<TagDTO> listTags;

    public ResponseProductDTO() {
    }

    public ResponseProductDTO(Long id, String title, String description, int quantity, long price, ResponseAccountProductDto creator, List<String> pictures, List<TagDTO> listTags) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.creator = creator;
        this.pictures = pictures;
        this.listTags = listTags;
    }

    public ResponseProductDTO(Long id, String title, String description, int quantity, long price, List<String> pictures) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
//        this.creator = creator;
        this.pictures = pictures;
//        this.listTags = listTags;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public ResponseAccountProductDto getCreator() {
        return creator;
    }

    public void setCreator(ResponseAccountProductDto creator) {
        this.creator = creator;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public List<TagDTO> getListTags() {
        return listTags;
    }

    public void setListTags(List<TagDTO> listTags) {
        this.listTags = listTags;
    }

}
