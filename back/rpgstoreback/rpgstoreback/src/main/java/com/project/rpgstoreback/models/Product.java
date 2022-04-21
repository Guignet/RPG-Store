package com.project.rpgstoreback.models;

import java.util.List;

public class Product {

    private Long id;
    private String title;
    private String description;
    private int quantity;
    private Account seller;

    private List<Tag> listTags;

}
