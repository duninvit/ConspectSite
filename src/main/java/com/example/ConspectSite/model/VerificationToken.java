package com.example.ConspectSite.model;

import javax.persistence.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
public class VerificationToken {

    private static final long EXPIRATION = TimeUnit.HOURS.toMillis(24);

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;

    private Date expiryDate;

    public VerificationToken(){
        expiryDate = new Date(new Date().getTime() + EXPIRATION);
    }

    public VerificationToken(User user, String token){
        expiryDate = new Date(new Date().getTime() + EXPIRATION);
        this.user = user;
        this.token = token;
    }

    public static long getEXPIRATION() {
        return EXPIRATION;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
