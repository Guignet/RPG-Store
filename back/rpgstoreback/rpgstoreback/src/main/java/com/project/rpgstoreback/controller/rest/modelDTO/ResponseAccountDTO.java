package com.project.rpgstoreback.controller.rest.modelDTO;

import com.project.rpgstoreback.models.Product;
import com.project.rpgstoreback.models.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResponseAccountDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private LocalDate registrationDate;
    private boolean isActive;
    private List<Role> roleList = new ArrayList<>();
    private List<ResponseProductDTO> listProduct = new ArrayList<>();

    public ResponseAccountDTO(){}

    public ResponseAccountDTO(Long id, String firstName, String lastName, String username, String password, String email, LocalDate registrationDate, boolean isActive, List<Role> roleList,  List<ResponseProductDTO> listProduct) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.registrationDate = registrationDate;
        this.isActive = isActive;
        this.roleList = roleList;
        this.listProduct = listProduct;
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

    public List<ResponseProductDTO> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<ResponseProductDTO> listProduct) {
        this.listProduct = listProduct;
    }

    public void addProduct(ResponseProductDTO product){
        this.listProduct.add(product);
    }

}