package com.example.ConspectSite.services.dto;

import com.example.ConspectSite.model.Comment;

public class CommentDTO {

    private String text;
    private String author;

    public CommentDTO(Comment comment){
        text = comment.getComment();
        author = comment.getUser().getUsername();
    }

    public CommentDTO() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
