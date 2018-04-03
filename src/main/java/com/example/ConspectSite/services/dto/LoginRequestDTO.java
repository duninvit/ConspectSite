package com.example.ConspectSite.services.dto;

import com.example.ConspectSite.model.User;

public class LoginRequestDTO extends UserAccountDTO {

    private String username;
    private String email;
    private String password;

    public LoginRequestDTO(User user){
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

    public LoginRequestDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public LoginRequestDTO(User user, String username, String email, String password) {
        super(user);
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public LoginRequestDTO() {
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
