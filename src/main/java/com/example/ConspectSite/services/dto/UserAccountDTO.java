package com.example.ConspectSite.services.dto;

import com.example.ConspectSite.model.User;

public class UserAccountDTO {

    private Long id;
    private String username;
    private String email;
    private String role;
    private boolean blocked;

    public UserAccountDTO() {
    }

    public UserAccountDTO(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getUserRole().name();
        this.blocked = !user.isNonBlocked();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
