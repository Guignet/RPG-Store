package com.project.rpgstoreback.models.form;

import com.project.rpgstoreback.models.Tag;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductForm {

    private String title;
    private String description;
    private int quantity;
    private Long price;
    private Long creator;
    private List<String> pictures;
    private List<Long> listTags;
    private int type;
    private int valForm;

    public int getValForm() {
        return valForm;
    }

    public void setValForm(int valForm) {
        this.valForm = valForm;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
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
}
