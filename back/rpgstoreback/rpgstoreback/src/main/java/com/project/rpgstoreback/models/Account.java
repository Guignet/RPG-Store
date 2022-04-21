package com.project.rpgstoreback.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private Long id;
    private String username;
    private String password;
    private String email;
    private LocalDate registrationDate;
    private boolean isActive;

    private List<Product> listProducts;
}
