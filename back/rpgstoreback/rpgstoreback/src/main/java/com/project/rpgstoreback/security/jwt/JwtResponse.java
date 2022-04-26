package com.project.rpgstoreback.security.jwt;

public class JwtResponse {
    private Long userId;
    private String username;
    private String token;

    public JwtResponse(Long userId, String username, String token) {
        this.userId = userId;
        this.username = username;
        this.token = token;
    }

    public JwtResponse() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
