package com.example.ConspectSite.model;

import com.example.ConspectSite.services.dto.RegisterRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column
    private String username;
    @Column
    @JsonIgnore
    private String password;
    @Column
    private String email;
    @Column
    @Enumerated(EnumType.STRING)
    private Role userRole;

    private boolean enabled;

    private boolean nonBlocked;

    public User(){
        this.nonBlocked = true;
    }

    public User(RegisterRequestDTO registerRequestDTO){
        this.username = registerRequestDTO.getUsername();
        this.email = registerRequestDTO.getEmail();
        this.password = registerRequestDTO.getPassword();
        userRole = Role.ROLE_USER;
        enabled = false;
        nonBlocked = true;
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

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public void setUserRole(String userRole){
        this.userRole = Role.valueOf(userRole);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isNonBlocked() {
        return nonBlocked;
    }

    public void setNonBlocked(boolean nonBlocked) {
        this.nonBlocked = nonBlocked;
    }
}
