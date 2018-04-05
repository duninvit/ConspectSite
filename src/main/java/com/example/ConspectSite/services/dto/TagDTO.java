package com.example.ConspectSite.services.dto;

import com.example.ConspectSite.model.Tag;

public class TagDTO {

    private String tag;
    private Integer weight;

    public TagDTO(Tag tag){
        this.tag = tag.getTag();
        this.weight = tag.getWeight();
    }

    public TagDTO() {
    }

    public TagDTO(String tag, Integer weight) {

        this.tag = tag;
        this.weight = weight;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
