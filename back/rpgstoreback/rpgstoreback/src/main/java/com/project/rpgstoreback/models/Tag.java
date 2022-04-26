package com.project.rpgstoreback.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String title;
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "listTags")
    private List<Product> listProducts = new ArrayList<>();

    public Tag(){}

    public Tag(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Tag(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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

    public List<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }



}
