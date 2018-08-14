package com.alloiz.palma.server.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class News extends BaseEntity<UserEntity>{

    @OneToMany(mappedBy = "news",cascade = CascadeType.ALL)
    private List<NewsTitle> newsTitles;

    @OneToMany(mappedBy = "news",cascade = CascadeType.ALL)
    private NewsDescription newsDescription;

    private Timestamp datetime;
    private String picturePath;

    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDatetime() {
        return datetime;
    }

    public News() {
    }

    public List<NewsTitle> getNewsTitles() {
        return newsTitles;
    }

    public News setNewsTitles(List<NewsTitle> newsTitles) {
        this.newsTitles = newsTitles;
        return this;
    }

    public NewsDescription getNewsDescription() {
        return newsDescription;
    }

    public News setNewsDescription(NewsDescription newsDescription) {
        this.newsDescription = newsDescription;
        return this;
    }

    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    public News setDatetime(Timestamp datetime) {
        this.datetime = datetime;
        return this;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public News setPicturePath(String picturePath) {
        this.picturePath = picturePath;
        return this;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsTitles=" + newsTitles +
                ", newsDescription=" + newsDescription +
                ", datetime=" + datetime +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
