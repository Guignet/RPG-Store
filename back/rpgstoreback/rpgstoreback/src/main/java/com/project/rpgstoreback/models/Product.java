package com.project.rpgstoreback.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Account creator; //seller ou admin

    private List<String> pictures;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Tag> listTags;

    public Product(){}

    public Product(String title, String description, int quantity, Account seller, List<String> pictures, List<Tag> listTags) {
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.creator = seller;
        this.pictures = pictures;
        this.listTags = listTags;
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

    public Account getCreator() {
        return creator;
    }

    public void setCreator(Account creator) {
        this.creator = creator;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public List<Tag> getListTags() {
        return listTags;
    }

    public void setListTags(List<Tag> listTags) {
        this.listTags = listTags;
    }
}
