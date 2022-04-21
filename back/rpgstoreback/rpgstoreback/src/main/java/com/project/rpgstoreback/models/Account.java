package com.project.rpgstoreback.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private LocalDate registrationDate;
    private boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER)//earger va cherche toute info de user + info des role associé
    private List<Role> roleList;

    //private List<Product> listProducts;

    public Account(){}

    public Account(String firstName, String lastName, String username, String password, String email, LocalDate registrationDate, boolean isActive, List<Role> roleList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.registrationDate = registrationDate;
        this.isActive = isActive;
        this.roleList = roleList;
    }
}
