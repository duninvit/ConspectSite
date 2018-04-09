package com.example.ConspectSite.services.dto;

import com.example.ConspectSite.model.Conspect;
import com.example.ConspectSite.model.Tag;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

public class ConspectRequestDTO {

    private String title;
    private String description;
    private String numberSpec;
    private String creationDate;
    private String mainText;
    private List<String> tags;

    public ConspectRequestDTO() {
    }

    public ConspectRequestDTO(Conspect conspect){
        title = conspect.getTitle();
        description = conspect.getDescription();
        numberSpec = conspect.getNumberSpec();
        creationDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(conspect.getCreationDate());
        tags = conspect.getTags().stream()
                .map(Tag::getTag)
                .collect(Collectors.toList());
        mainText = conspect.getMainText();
    }

    public ConspectRequestDTO(String author, String title, String description, String numberSpec, String creationDate, String mainText, List<String> tags) {
        this.title = title;
        this.description = description;
        this.numberSpec = numberSpec;
        this.creationDate = creationDate;
        this.mainText = mainText;
        this.tags = tags;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
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
}
