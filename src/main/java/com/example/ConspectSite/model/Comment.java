package com.example.ConspectSite.model;

import com.example.ConspectSite.services.dto.CommentDTO;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String comment;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(targetEntity = Conspect.class, fetch = FetchType.EAGER)
    private Conspect conspect;

    public Comment(CommentDTO commentDTO){
        this.comment = commentDTO.getText();
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
