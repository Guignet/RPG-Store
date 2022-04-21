package com.project.rpgstoreback.models;

import java.util.List;

public abstract class Product {

    private Long id;
    private String title;
    private String description;
    private int quantity;
    private Account seller;
    private String[] pictures;

    private List<Tag> listTags;




}
