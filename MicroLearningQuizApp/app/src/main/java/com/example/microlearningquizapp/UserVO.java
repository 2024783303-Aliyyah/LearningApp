package com.example.microlearningquizapp;

public class UserVO {

    private String username;
    private String password;
    private String role;

    public UserVO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
