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

    @OneToMany(mappedBy = "mainPage",cascade = CascadeType.ALL)
    private List<SEO> seos;

    public MainPage() {
    }

    public List<Image> getImages() {
        return images;
    }

    public MainPage setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public List<SEO> getSeos() {
        return seos;
    }

    public MainPage setSeos(List<SEO> seos) {
        this.seos = seos;
        return this;
    }

    @Override
    public String toString() {
        return "MainPage{" +
                "images=" + (images == null ? "null" : images) +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
