package com.project.rpgstoreback.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Prénom ne peut pas être vide")
    private String firstName;

    @NotBlank(message = "Nom ne peut pas être vide")
    private String lastName;

    @NotBlank(message = "Pseudo ne peut pas être vide")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Mot de passe ne peut pas être vide")
    @Size(min = 4, message = "Mot de passe trop court")
    private String password;

    @Column(unique = true)
    @NotBlank(message = "Email ne peut pas être vide")
    private String email;

    @NotNull//verif
    private LocalDate registrationDate;

    private boolean isActive;

    @NotEmpty(message = "Le role ne peut pas être vide")
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roleList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="panierUser")
    private List<Product> listProducts = new ArrayList<>(); // liste panier pour user

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Product> listProducts) {
        this.listProducts = listProducts;
    }

    public void addProduct(Product product){
        this.listProducts.add(product);
    }
}
