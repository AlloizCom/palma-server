package com.alloiz.palma.server.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class MainPage extends BaseEntity<MainPage>{

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "room_id")
    private List<Image> images;

    public MainPage() {
    }

    public List<Image> getImages() {
        return images;
    }

    public MainPage setImages(List<Image> images) {
        this.images = images;
        return this;
    }

    @Override
    public String toString() {
        return "MainPage{" +
                ", images=" + (images == null ? "null" : images) +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
