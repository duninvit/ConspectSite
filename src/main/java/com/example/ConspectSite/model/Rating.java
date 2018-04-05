package com.example.ConspectSite.model;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity
public class Rating {

    @Id
    @GeneratedValue
    private Long id;

    @Max(value = 5)
    private Integer rate;

    @OneToOne(targetEntity = User.class)
    private User user;

    @ManyToOne(targetEntity = Conspect.class)
    private Conspect conspect;

    public Rating(Integer rate, User user, Conspect conspect){
        this.rate = rate;
        this.user = user;
        this.conspect = conspect;
    }

    public Rating() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Conspect getConspect() {
        return conspect;
    }

    public void setConspect(Conspect conspect) {
        this.conspect = conspect;
    }
}
