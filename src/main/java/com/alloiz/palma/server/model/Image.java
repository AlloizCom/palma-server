package com.alloiz.palma.server.model;

import javax.persistence.Entity;

@Entity
public class Image extends BaseEntity<Image>{

    private String path;

    public Image() {
    }

    public String getPath() {
        return path;
    }

    public Image setPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public String toString() {
        return "Image{" +
                "path='" + path + '\'' +
                ", id=" + id +
                ", available=" + available +
                '}';
    }
}
