package com.project.rpgstoreback.controller.rest.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SigninRequest {

    @NotBlank
    @Size(min = 3)
    private String username;

    @NotBlank
    @Size(min = 5)
    private String password;


    public SigninRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public SigninRequest() {
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
}
