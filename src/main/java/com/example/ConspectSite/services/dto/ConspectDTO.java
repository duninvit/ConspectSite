package com.example.ConspectSite.services.dto;

import com.example.ConspectSite.model.Comment;
import com.example.ConspectSite.model.Conspect;
import com.example.ConspectSite.model.Tag;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ConspectDTO {

    private Long id;
    private String author;
    private String title;
    private String description;
    private String numberSpec;
    private String creationDate;
    private String mainText;
    private List<String> tags;
    private List<CommentDTO> comments;

    public ConspectDTO(Conspect conspect){
        id = conspect.getId();
        title = conspect.getTitle();
        description = conspect.getDescription();
        numberSpec = conspect.getNumberSpec();
        creationDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(conspect.getCreationDate());
        tags = conspect.getTags().stream()
                .map(Tag::getTag)
                .collect(Collectors.toList());
        mainText = conspect.getMainText();
        author = conspect.getUser().getUsername();
    }

    public void setComments(Iterable<Comment> comments){
        this.comments = StreamSupport.stream(comments.spliterator(), false)
                .map(CommentDTO::new)
                .collect(Collectors.toList());
    }

    public ConspectDTO() {
    }

    public ConspectDTO(Long id, String author, String title, String description, String numberSpec, String creationDate, String mainText, List<String> tags, List<CommentDTO> comments) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.description = description;
        this.numberSpec = numberSpec;
        this.creationDate = creationDate;
        this.tags = tags;
        this.comments = comments;
        this.mainText = mainText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }
}
