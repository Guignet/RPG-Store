package com.project.rpgstoreback.controller.rest.modelDTO;

import java.util.ArrayList;
import java.util.List;

public class CreateUpdateArmorDTO {
    private Long id;
    private String title;
    private String description;
    private int quantity;
    private long price;
    private Long creator; //seller ou admin
    private List<String> pictures = new ArrayList<>();
    private List<Long> listTags = new ArrayList<>(); //id des Tags
    private int resistance;

    public CreateUpdateArmorDTO(){}

    public CreateUpdateArmorDTO(Long id, String title, String description, int quantity, long price, Long creator, List<String> pictures, List<Long> listTags, int resistance) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.creator = creator;
        this.pictures = pictures;
        this.listTags = listTags;
        this.resistance = resistance;
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

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public List<Long> getListTags() {
        return listTags;
    }

    public void setListTags(List<Long> listTags) {
        this.listTags = listTags;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }
}
