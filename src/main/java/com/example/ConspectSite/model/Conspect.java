package com.example.ConspectSite.model;

import com.example.ConspectSite.services.dto.ConspectDTO;
import com.example.ConspectSite.services.dto.ConspectRequestDTO;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class Conspect {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User user;

    private String title;

    @Size(max = 280)
    private String description;

    @Size(max = 10)
    private String numberSpec;

    @ManyToMany(targetEntity = Tag.class, fetch = FetchType.EAGER)
    private List<Tag> tags;

    private String mainText;

    private Date creationDate;

    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @OneToMany(targetEntity = Rating.class, fetch = FetchType.LAZY)
    private List<Rating> rates;

    public Conspect(ConspectRequestDTO conspectRequestDTO, User user){
        this.user = user;
        setDataFromDTO(conspectRequestDTO);
    }

    public void setDataFromDTO(ConspectRequestDTO conspectRequestDTO){
        this.title = conspectRequestDTO.getTitle();
        this.description = conspectRequestDTO.getDescription();
        this.numberSpec = conspectRequestDTO.getNumberSpec();
        this.mainText = conspectRequestDTO.getMainText();
        this.creationDate = new Date();
    }

    public void setDataFromDTO(ConspectDTO conspectDTO){
        this.id = conspectDTO.getId();
        this.title = conspectDTO.getTitle();
        this.description = conspectDTO.getDescription();
        this.numberSpec = conspectDTO.getNumberSpec();
        this.mainText = conspectDTO.getMainText();
        this.creationDate = new Date();
    }

    public Conspect() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumberSpec() {
        return numberSpec;
    }

    public void setNumberSpec(String numberSpec) {
        this.numberSpec = numberSpec;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Rating> getRates() {
        return rates;
    }

    public void setRates(List<Rating> rates) {
        this.rates = rates;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }
}
