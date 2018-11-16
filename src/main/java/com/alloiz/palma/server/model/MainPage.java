package com.alloiz.palma.server.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class MainPage extends BaseEntity<MainPage>{

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "main_page_id")
    private List<Image> images;

    private String keywords;
    private String description;

    public MainPage() {
    }

    public List<Image> getImages() {
        return images;
    }

    public MainPage setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public String getKeywords() {
        return keywords;
    }

    public MainPage setKeywords(String keywords) {
        this.keywords = keywords;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MainPage setDescription(String description) {
        this.description = description;
        return this;
    }


    @Override
    public String toString() {
        return "MainPage{" +
                "images=" + (images == null ? "null" : images) +
                ", keywords='" + keywords + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
