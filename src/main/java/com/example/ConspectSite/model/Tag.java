package com.example.ConspectSite.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Tag {

    @Id
    private String tag;

    private Integer weight;

    @ManyToMany(targetEntity = Conspect.class, fetch = FetchType.LAZY)
    private List<Conspect> conspects;

    public Tag(String tag, int weight){
        this.tag = tag;
        this.weight = weight;
    }

    public Tag() {
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

    public List<Conspect> getConspects() {
        return conspects;
    }

    public void setConspects(List<Conspect> conspects) {
        this.conspects = conspects;
    }
}
